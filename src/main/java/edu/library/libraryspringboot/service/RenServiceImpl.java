package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.*;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.NotifRepository;
import edu.library.libraryspringboot.repository.RenRepository;
import edu.library.libraryspringboot.repository.VAdminHisRepository;
import edu.library.libraryspringboot.repository.VHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RenServiceImpl implements RenService {

    private final RenRepository rr;
    private final ModelMapper mm;
    private final VHistoryRepository v_hr;
    private final VAdminHisRepository v_ahr;

    @Override
    public int getRentCount(String uId) {
        int count = rr.findRentCount(uId);
        return count;
    }

    @Override
    public int getRentHstryCount(String uId) {
        int count = rr.findRentHisCount(uId);
        return count;
    }

    @Override
    public void register(RentalDTO renDTO) {
        Rental ren = mm.map(renDTO, Rental.class);
        rr.save(ren);
    }

    /*@Override
    public List<Object[]> list(String uId) {
        List<Object[]> list = rr.rentalList(uId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            LocalDateTime rRentalDateTime = (LocalDateTime) row[3];
            LocalDateTime rWhenToReturnDateTime = (LocalDateTime) row[4];
            Boolean rIsExtended = (Boolean) row[5];
            Boolean rIsReturnReq = (Boolean) row[6];

            String rRentalDate = rRentalDateTime.format(formatter);
            String rWhenToReturn = rWhenToReturnDateTime.format(formatter);

            row[3] = rRentalDate;
            row[4] = rWhenToReturn;
        }
        return list;
    }*/

    @Override
    public List<Object[]> list(String uId) {
        List<Object[]> list = rr.rentalList(uId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            LocalDateTime rRentalDateTime = (LocalDateTime) row[3];
            LocalDateTime rWhenToReturnDateTime = (LocalDateTime) row[4];
            Boolean rIsExtended = (Boolean) row[5];
            Boolean rIsReturnReq = (Boolean) row[6];
            Boolean rIsExtReq = (Boolean) row[7];
            LocalDateTime rRtnReqDateTime = (LocalDateTime) row[8];
            LocalDateTime rExtReqDateTime = (LocalDateTime) row[9];

            String rRentalDate = rRentalDateTime.format(formatter);
            String rWhenToReturn = rWhenToReturnDateTime.format(formatter);
            String rRtnReqDate = rRtnReqDateTime != null ? rRtnReqDateTime.format(formatter) : null;
            String rExtReqDate = rExtReqDateTime != null ? rExtReqDateTime.format(formatter) : null;

            row[3] = rRentalDate;
            row[4] = rWhenToReturn;
            row[8] = rRtnReqDate;
            row[9] = rExtReqDate;
        }
        return list;
    }

    /*@Override
    public PageResponseDTO<VHistoryDTO> historyList(PageRequestDTO pageRequestDTO, String uId) {
        List<Object[]> list = rr.historyList(uId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            LocalDateTime rRentalDateTime = (LocalDateTime) row[3];
            LocalDateTime rWhenToReturnDateTime = (LocalDateTime) row[4];
            LocalDateTime rReturnDateTime = (LocalDateTime) row[5];
            Boolean rIsExtended = (Boolean) row[6];

            String rRentalDate = rRentalDateTime.format(formatter);
            String rWhenToReturn = rWhenToReturnDateTime.format(formatter);
            String rReturnDate = rReturnDateTime.format(formatter);

            row[3] = rRentalDate;
            row[4] = rWhenToReturn;
            row[5] = rReturnDate;
        }
        return list;
    }*/

    @Override
    public PageResponseDTO<VHistoryDTO> historyList(PageRequestDTO pageRequestDTO, String uId) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        Boolean check = pageRequestDTO.getCheck();

        log.info("types:" + types);
        log.info("keyword:" + keyword);
        log.info("check:" + check);


        Page<VHistory> result = v_hr.searchAll(types, keyword, pageable, check, uId);

        List<VHistoryDTO> dtoList = result.getContent().stream()
                .map(vHistory -> mm.map(vHistory, VHistoryDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VHistoryDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void setRtnReq(String uId, int bNo) {
        rr.updateRtnReq(uId, bNo);
    }

    @Override
    public void setExtReq(String uId, int bNo) {
        rr.updateExtReq(uId, bNo);
    }

    @Override
    public void setExtStatus(String uId, int bNo) {
        rr.updateExtStatus(uId, bNo);
    }

    @Override
    public void setRtnStatus(String uId, Integer bNo) {
        rr.updateRtnStatus(uId, bNo);
    }

    @Override
    public int getHisCnt(String uId) {
        return v_hr.countByuId(uId);
    }

    @Override
    public PageResponseDTO<VAdminHistoryDTO> adminHisList(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        String choose = pageRequestDTO.getChoose();

        log.info("types:" + types);
        log.info("keyword:" + keyword);
        log.info("check:" + choose);


        Page<VAdminHistory> result = v_ahr.searchAll(types, keyword, pageable, choose);

        List<VAdminHistoryDTO> dtoList = result.getContent().stream()
                .map(vAdminHis -> mm.map(vAdminHis, VAdminHistoryDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VAdminHistoryDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public long getAllCount() {
        return rr.count();
    }
}

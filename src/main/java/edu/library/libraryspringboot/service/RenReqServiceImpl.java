package edu.library.libraryspringboot.service;
import edu.library.libraryspringboot.domain.RentalRequest;
import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.RenReqRepository;
import edu.library.libraryspringboot.repository.VAdminRenReqRepository;
import edu.library.libraryspringboot.repository.VRenReqRepository;
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
public class RenReqServiceImpl implements RenReqService {

    private final RenReqRepository rr;
    private final VRenReqRepository vw_rr;
    private final VAdminRenReqRepository v_arr;
    private final ModelMapper mm;

    @Override
    public void register(RentalRequestDTO renReqDTO) {
        RentalRequest renReq = mm.map(renReqDTO, RentalRequest.class);
        rr.save(renReq);
    }

    @Override
    public int isRequested(String uId, int bNo) {
        return rr.requestCheck(uId, bNo);
    }

    @Override
    public int getReqCount(String uId) {
        int count = rr.findReqCount(uId);
        log.info("............................");
        log.info(count);
        return count;
    }

    @Override
    public int getAllReqCount() {
        return rr.findAllReqCount();
    }

    @Override
    public void removeIncompleteList(String uId) {
        rr.deleteIncompleteList(uId);
        log.info("remove Incomplete Rental request.............");
    }

    /*@Override
    public List<Object[]> list(String uId) {
        List<Object[]> list = rr.currReqList(uId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            String bTitle = (String) row[0];
            String bAuthor = (String) row[1];
            LocalDateTime rReqDateTime = (LocalDateTime) row[2];  // Assuming row[2] is a LocalDateTime
            Integer bNo = (Integer) row[3];

            String rReqDate = rReqDateTime.format(formatter);  // Format LocalDateTime to string

            // Update the row with formatted date if needed
            row[2] = rReqDate;
        }
        return list;
    }*/

    @Override
    public PageResponseDTO<VRentalRequestDTO> list(PageRequestDTO pageRequestDTO, String uId) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<VRentalRequest> result = vw_rr.searchAll(types, keyword, pageable, uId);

        List<VRentalRequestDTO> dtoList = result.getContent().stream()
                .map(renReqView -> mm.map(renReqView, VRentalRequestDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VRentalRequestDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    /*@Override
    public List<Object[]> ListAll() {
        List<Object[]> list = rr.currAllReqList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            String bTitle = (String) row[0];
            String bAuthor = (String) row[1];
            Integer bNo = (Integer) row[2];
            String uId = (String) row[3];
            LocalDateTime rReqDateTime = (LocalDateTime) row[4];  // row[4] is LocalDateTime
            Boolean bIsRental = (Boolean) row[5];
            Long renCnt = (Long) row[6];  // row[6] is Long, not Integer
            Integer uNo = (Integer) row[7];  // row[7] is Integer
            Boolean uIsBlacklist = (Boolean) row[8];  // row[8] is Boolean

            String rReqDate = rReqDateTime.format(formatter);  // Format LocalDateTime to string

            // Update the row with formatted date if needed
            row[4] = rReqDate;
        }
        return list;
    }*/

    @Override
    public PageResponseDTO<VAdminRentalRequestDTO> listAll(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        Boolean check = pageRequestDTO.getCheck();

        Page<VAdminRentalRequest> result = v_arr.searchAll(types, keyword, pageable, check);

        List<VAdminRentalRequestDTO> dtoList = result.getContent().stream()
                .map(renReqView -> mm.map(renReqView, VAdminRentalRequestDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VAdminRentalRequestDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void setRenReq(String uId, int bNo) {
        rr.updateRenReq(uId, bNo);
    }

    @Override
    public void removeRenReq(String uId, int bNo) {
        rr.deleteRenReq(uId, bNo);
    }
}

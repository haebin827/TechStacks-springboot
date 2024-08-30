package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.ReturnRequest;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.domain.VAdminReturnRequest;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.RtnReqRepository;
import edu.library.libraryspringboot.repository.VAdminRtnReqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RtnReqServiceImpl implements RtnReqService{

    private final RtnReqRepository rr;
    private final VAdminRtnReqRepository v_arr;
    private final ModelMapper mm;

    @Override
    public void register(ReturnRequestDTO rtnDTO) {
        ReturnRequest rtn = mm.map(rtnDTO, ReturnRequest.class);
        rr.save(rtn);
    }

    @Override
    public void remove(String uId, int bNo) {
        rr.delete(uId, bNo);
    }

    @Override
    public LocalDate getRtnReqDate(int bNo, String uId) {
        LocalDate date = rr.selectRtnReqDate(bNo, uId);
        return date;
    }

    @Override
    public int getAllReqCount() {
        return rr.findAllReqCount();
    }

    /*@Override
    public List<Object[]> ListAll() {

        //b.bTitle, b.bAuthor, b.bNo, e.uId, e.rReqDate, u.uNo, u.uIsBlacklist
        List<Object[]> list = rr.currAllReqList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            String bTitle = (String) row[0];
            String bAuthor = (String) row[1];
            Integer bNo = (Integer) row[2];
            String uId = (String) row[3];
            LocalDateTime rReqDateTime = (LocalDateTime) row[4];  // row[4] is LocalDateTime
            Integer uNo = (Integer) row[5];  // row[7] is Integer
            Boolean uIsBlacklist = (Boolean) row[6];  // row[8] is Boolean
            LocalDateTime rWhenToReturnTime = (LocalDateTime) row[7];
            String bCondition = (String) row[8];

            String rReqDate = rReqDateTime.format(formatter);  // Format LocalDateTime to string
            String rWhenToReturn = rWhenToReturnTime.format(formatter);

            // Update the row with formatted date if needed
            row[4] = rReqDate;
            row[7] = rWhenToReturn;
        }
        return list;
    }*/

    @Override
    public PageResponseDTO<VAdminReturnRequestDTO> listAll(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<VAdminReturnRequest> result = v_arr.searchAll(types, keyword, pageable);

        List<VAdminReturnRequestDTO> dtoList = result.getContent().stream()
                .map(rtnReqView -> mm.map(rtnReqView, VAdminReturnRequestDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VAdminReturnRequestDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void setRtnReq(String uId, Integer bNo) {
        rr.updateRtnReq(uId, bNo);
    }
}

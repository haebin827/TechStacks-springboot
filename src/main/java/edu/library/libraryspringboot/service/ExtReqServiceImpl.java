package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.ExtensionRequest;
import edu.library.libraryspringboot.domain.ReturnRequest;
import edu.library.libraryspringboot.domain.VAdminExtensionRequest;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.ExtReqRepository;
import edu.library.libraryspringboot.repository.VAdminExtReqRepository;
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
public class ExtReqServiceImpl implements ExtReqService {

    private final ExtReqRepository er;
    private final VAdminExtReqRepository v_err;
    private final ModelMapper mm;

    @Override
    public void register(ExtensionRequestDTO extDTO) {
        ExtensionRequest ext = mm.map(extDTO, ExtensionRequest.class);
        er.save(ext);
    }

    @Override
    public void remove(String uId, int bNo) {
        er.delete(uId, bNo);
    }

    @Override
    public int getAllReqCount() {
        return er.countByrExtReqTrue();
    }

    /*@Override
    public List<Object[]> ListAll() {

        //b.bTitle, b.bAuthor, b.bNo, e.uId, e.rReqDate, u.uNo, u.uIsBlacklist
        List<Object[]> list = er.currAllReqList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] row : list) {
            String bTitle = (String) row[0];
            String bAuthor = (String) row[1];
            Integer bNo = (Integer) row[2];
            String uId = (String) row[3];
            LocalDateTime rReqDateTime = (LocalDateTime) row[4];  // row[4] is LocalDateTime
            Integer uNo = (Integer) row[5];  // row[7] is Integer
            Boolean uIsBlacklist = (Boolean) row[6];  // row[8] is Boolean

            String rReqDate = rReqDateTime.format(formatter);  // Format LocalDateTime to string

            // Update the row with formatted date if needed
            row[4] = rReqDate;
        }
        return list;
    }*/

    @Override
    public PageResponseDTO<VAdminExtensionRequestDTO> listAll(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        Boolean check = pageRequestDTO.getCheck();

        Page<VAdminExtensionRequest> result = v_err.searchAll(types, keyword, pageable);

        List<VAdminExtensionRequestDTO> dtoList = result.getContent().stream()
                .map(extReqView -> mm.map(extReqView, VAdminExtensionRequestDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VAdminExtensionRequestDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void setExtReq(String uId, int bNo) {
        er.updateExtReq(uId, bNo);
    }
}

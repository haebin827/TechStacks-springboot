package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface RtnReqService {

    void register(ReturnRequestDTO rtnDTO);

    LocalDate getRtnReqDate(int bNo, String uId);

    void remove(String uId, int bNo);

    int getAllReqCount();

    /*List<Object[]> ListAll();*/
    PageResponseDTO<VAdminReturnRequestDTO> listAll(PageRequestDTO pageRequestDTO);

    void setRtnReq(String uId, Integer bNo);
}

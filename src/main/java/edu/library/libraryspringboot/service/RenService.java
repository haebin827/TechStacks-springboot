package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RenService {

    int getRentCount(String uId);

    int getRentHstryCount(String uId);

    void register(RentalDTO rentDTO);

    List<Object[]> list(String uId);

    /*List<Object[]> historyList(String uId);*/

    //for user
    PageResponseDTO<VHistoryDTO> historyList(PageRequestDTO pageRequestDTO, String uId);

    //for admin
    PageResponseDTO<VAdminHistoryDTO> adminHisList(PageRequestDTO pageRequestDTO);

    void setRtnReq(String uId, int bNo);

    void setExtReq(String uId, int bNo);

    void setExtStatus(String uId, int bNo);

    void setRtnStatus(String uId, Integer bNo);

    int getHisCnt(String uId);

    int getAllCount();
}
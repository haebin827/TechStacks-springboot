package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;

public interface RenReqService {

    void register(RentalRequestDTO renReqDTO);

    int isRequested(String uId, int bNo);

    int getReqCount(String uId);

    int getAllReqCount();

    void removeIncompleteList(String uId);

    // pagination 테스트를 위해 주석처리
    //List<Object[]> list(String uId);
    PageResponseDTO<VRentalRequestDTO> list(PageRequestDTO pageRequestDTO, String uId);

    /*List<Object[]> ListAll();*/
    PageResponseDTO<VAdminRentalRequestDTO> listAll(PageRequestDTO pageRequestDTO);

    void setRenReq(String uId, int bNo);

    void removeRenReq(String uId, int bNo);
}

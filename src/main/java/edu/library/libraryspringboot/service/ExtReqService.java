package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;

import java.util.List;

public interface ExtReqService {

    void register(ExtensionRequestDTO extDTO);

    void remove(String uId, int bNo);

    int getAllReqCount();

List<Object[]> ListAll();

    PageResponseDTO<VAdminExtensionRequestDTO> listAll(PageRequestDTO pageRequestDTO);

    void setExtReq(String uId, int bNo);
}

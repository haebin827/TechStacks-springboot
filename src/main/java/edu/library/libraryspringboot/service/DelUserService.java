package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.dto.DeletedUserDTO;
import edu.library.libraryspringboot.dto.FaqDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;

public interface DelUserService {

    int register(DeletedUserDTO delUserDTO);

    DeletedUserDTO readOne(int dId);

    PageResponseDTO<DeletedUserDTO> list(PageRequestDTO pageRequestDTO);

    DeletedUserDTO readOneByUId(String uId);
}

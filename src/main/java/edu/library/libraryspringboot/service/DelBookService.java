package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.DeletedBookDTO;
import edu.library.libraryspringboot.dto.DeletedUserDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;

public interface DelBookService {

    int register(DeletedBookDTO delBookDTO);

    DeletedBookDTO readOne(int dId);

    PageResponseDTO<DeletedBookDTO> list(PageRequestDTO pageRequestDTO);
}

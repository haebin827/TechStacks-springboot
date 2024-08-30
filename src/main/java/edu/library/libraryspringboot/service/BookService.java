package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import org.springframework.data.repository.query.Param;

public interface BookService {

    int register(BookDTO bookDTO);

    BookDTO readOne(int bNo);

    void modify(BookDTO bookDTO);

    void remove(int bNo);

    PageResponseDTO<BookDTO> list(PageRequestDTO pageRequestDTO);

    void modifyActiveStatus(int bNo);

    void setRenStatus(int bNo);

    void setRtnStatus(int bNo, String bCondition);
}

package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.VHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VHistorySearch {

    Page<VHistory> searchAll(String[] types, String keyword, Pageable pageable, Boolean check, String uId);
}

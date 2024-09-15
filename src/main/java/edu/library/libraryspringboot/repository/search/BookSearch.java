package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookSearch {

    Page<Book> search1(Pageable pageable);

    Page<Book> searchAll(String[] types, String keyword, Pageable pageable, Boolean check, String cat);
}

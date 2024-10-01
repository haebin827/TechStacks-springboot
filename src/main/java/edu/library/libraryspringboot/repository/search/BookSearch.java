package edu.library.libraryspringboot.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.library.libraryspringboot.domain.Book;

public interface BookSearch {

    /*Page<Book> search1(Pageable pageable);*/

    Page<Book> searchAll(String[] types, String keyword, Pageable pageable, Boolean check, String cat);
}

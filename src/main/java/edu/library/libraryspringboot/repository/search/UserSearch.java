package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSearch {

    Page<User> search1(Pageable pageable);

    Page<User> searchAll(String[] types, String keyword, Pageable pageable, Boolean check);
}

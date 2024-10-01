package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.DeletedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DelUserSearch {

    Page<DeletedUser> searchAll(String[] types, String keyword, Pageable pageable);
}

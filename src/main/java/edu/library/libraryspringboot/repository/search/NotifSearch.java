package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotifSearch {

    Page<Notification> search1(Pageable pageable);

    Page<Notification> searchAll(String keyword, Pageable pageable);
}

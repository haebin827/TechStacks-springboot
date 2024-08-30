package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VAdminRenReqSearch {

    Page<VAdminRentalRequest> searchAll(String[] types, String keyword, Pageable pageable, Boolean check);
}

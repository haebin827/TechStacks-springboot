package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.VAdminExtensionRequest;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VAdminExtReqSearch {

    Page<VAdminExtensionRequest> searchAll(String[] types, String keyword, Pageable pageable);
}

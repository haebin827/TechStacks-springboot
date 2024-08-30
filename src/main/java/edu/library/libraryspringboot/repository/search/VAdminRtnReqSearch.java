package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.VAdminReturnRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VAdminRtnReqSearch {

    Page<VAdminReturnRequest> searchAll(String[] types, String keyword, Pageable pageable);
}

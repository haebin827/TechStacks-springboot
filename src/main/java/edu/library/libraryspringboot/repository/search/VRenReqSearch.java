package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.VRentalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VRenReqSearch {

    Page<VRentalRequest> searchAll(String[] types, String keyword, Pageable pageable, String uId);
}

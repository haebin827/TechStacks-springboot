package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.domain.VWishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VWishlistSearch {

    Page<VWishlist> searchAll(Pageable pageable, String uId);
}

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VWishlist;
import edu.library.libraryspringboot.repository.search.VWishlistSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VWishlistRepository extends JpaRepository<VWishlist, Integer>, VWishlistSearch {
}

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.domain.VHistory;
import edu.library.libraryspringboot.repository.search.VAdminRenReqSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VAdminRenReqRepository extends JpaRepository<VHistory, Integer>, VAdminRenReqSearch {
}

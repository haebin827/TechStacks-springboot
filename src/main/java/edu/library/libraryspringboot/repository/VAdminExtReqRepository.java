package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VAdminExtensionRequest;
import edu.library.libraryspringboot.domain.VHistory;
import edu.library.libraryspringboot.repository.search.VAdminExtReqSearch;
import edu.library.libraryspringboot.repository.search.VAdminRenReqSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VAdminExtReqRepository extends JpaRepository<VAdminExtensionRequest, Integer>, VAdminExtReqSearch {
}

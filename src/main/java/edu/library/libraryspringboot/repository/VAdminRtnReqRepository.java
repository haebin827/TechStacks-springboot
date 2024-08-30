package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VAdminReturnRequest;
import edu.library.libraryspringboot.domain.VHistory;
import edu.library.libraryspringboot.repository.search.VAdminRenReqSearch;
import edu.library.libraryspringboot.repository.search.VAdminRtnReqSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VAdminRtnReqRepository extends JpaRepository<VAdminReturnRequest, Integer>, VAdminRtnReqSearch {
}

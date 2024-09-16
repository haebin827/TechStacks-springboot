package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.repository.search.VRenReqSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VRenReqRepository extends JpaRepository<VRentalRequest, Integer>, VRenReqSearch {

    int countByuId(String uId);

    Page<VRentalRequest> findByuId(String uId, Pageable pageable);
}

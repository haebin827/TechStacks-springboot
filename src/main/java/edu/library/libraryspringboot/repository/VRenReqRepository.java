package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.repository.search.VRenReqSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VRenReqRepository extends JpaRepository<VRentalRequest, Integer>, VRenReqSearch {

    @Query("select count(*) from VRentalRequest where u_id = :uId")
    int requestCheck(String uId);

    @Query("SELECT r FROM VRentalRequest r WHERE r.u_id = :uId")
    Page<VRentalRequest> findByUId(@Param("uId") String uId, Pageable pageable);
}

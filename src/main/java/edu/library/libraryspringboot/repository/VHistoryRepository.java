package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VHistory;
import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.repository.search.VHistorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VHistoryRepository extends JpaRepository<VHistory, Integer>, VHistorySearch {

    Page<VHistory> findByuId(String uId, Pageable pageable);

    int countByuId(String uId);
}

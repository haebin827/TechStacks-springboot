package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.VAdminHistory;
import edu.library.libraryspringboot.repository.search.VAdminHisSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VAdminHisRepository extends JpaRepository<VAdminHistory, Integer>, VAdminHisSearch {
}

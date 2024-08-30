package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Notification;
import edu.library.libraryspringboot.repository.search.NotifSearch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotifRepository extends JpaRepository<Notification, Integer>, NotifSearch {

    @Query(value="select now()", nativeQuery = true)
    String getTime();

    @Modifying
    @Transactional
    @Query("update Notification set nViews = nViews + 1 where nNo = :nNo")
    void updateViews(int nNo);
}

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.repository.search.DelUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DelUserRepository extends JpaRepository<DeletedUser, Integer>, DelUserSearch {

    @Query("select d from DeletedUser d where d.uId = :uId")
    DeletedUser selectOneByUId(String uId);
}

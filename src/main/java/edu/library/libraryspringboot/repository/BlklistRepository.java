package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlklistRepository extends JpaRepository<Blacklist, Integer> {

    @Query("select b.blReason from Blacklist as b where b.uId IN(select u.uId from User as u where u.uNo = :uNo)")
    String findUId(int uNo);

    @Query("select b from Blacklist b where b.uId = :uId order by b.blRegDate desc")
    List<Blacklist> selectAllByUId(String uId);
}


package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.ExtensionRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExtReqRepository extends JpaRepository<ExtensionRequest, Integer> {

    @Modifying
    @Transactional
    @Query("delete ExtensionRequest where uId = :uId and bNo = :bNo and rExtReq = true")
    void delete(@Param("uId") String uId, @Param("bNo") int bNo);

    int countByrExtReqTrue();

    @Query("select b.bTitle, b.bAuthor, b.bNo, e.uId, e.rReqDate, u.uNo, u.uIsBlacklist " +
            "from ExtensionRequest e " +
            "join Book b on e.bNo = b.bNo " +
            "join User u on e.uId = u.uId " +  // Join with User table
            "where e.rExtReq = true and b.bIsActive = true and b.bIsRental = true")
    List<Object[]> currAllReqList();

    @Modifying
    @Transactional
    @Query("update ExtensionRequest set rExtReq = false where uId = :uId and bNo = :bNo and rExtReq = true")
    void updateExtReq(@Param("uId") String uId, @Param("bNo") Integer bNo);
}

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.ReturnRequest;
import edu.library.libraryspringboot.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RtnReqRepository extends JpaRepository<ReturnRequest, Integer> {

    //unused
    @Query("select rReqDate from ReturnRequest where bNo = :bNo and uId = :uId and rRtnReq = true")
    LocalDate selectRtnReqDate(@Param("bNo") int bNo, @Param("uId") String uIds);

    @Modifying
    @Transactional
    @Query("delete ReturnRequest where uId = :uId and bNo = :bNo and rRtnReq = true")
    void delete(@Param("uId") String uId, @Param("bNo") int bNo);

    @Query("select count(*) from ReturnRequest where rRtnReq = true")
    int findAllReqCount();

    /*@Query("select b.bTitle, b.bAuthor, b.bNo, rr.uId, rr.rReqDate, u.uNo, u.uIsBlacklist, r.rWhenToReturn, b.bCondition " +
            "from ReturnRequest rr " +
            "join Book b on rr.bNo = b.bNo " +
            "join User u on rr.uId = u.uId " +  // Join with User table
            "join Rental r on rr.bNo = r.bNo and rr.uId = r.uId " +
            "where rr.rRtnReq = true and b.bIsActive = true and b.bIsRental = true and r.rIsReturnReq = true")
    List<Object[]> currAllReqList();*/

    @Modifying
    @Transactional
    @Query("update ReturnRequest set rRtnReq = false where uId = :uId and bNo = :bNo and rRtnReq = true")
    void updateRtnReq(@Param("uId") String uId, @Param("bNo") Integer bNo);
}

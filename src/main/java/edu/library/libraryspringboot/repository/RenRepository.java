package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Rental;
import edu.library.libraryspringboot.dto.RentalDTO;
import edu.library.libraryspringboot.repository.search.RenSearch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RenRepository extends JpaRepository<Rental, Long>, RenSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    @Query("select count(*) from Rental where uId = :uId and rIsReturned = false")
    int findRentCount(@Param("uId") String uId);

    @Query("select count(*) from Rental where uId = :uId and rIsReturned = true")
    int findRentHstryCount(@Param("uId") String uId);

    @Modifying
    @Transactional
    @Query("update Rental set rIsReturnReq = case when rIsReturnReq = true then false else true end where uId = :uId and bNo = :bNo and rIsReturned = false")
    void updateRtnReq(@Param("uId") String uId, @Param("bNo") int bNo);

    @Modifying
    @Transactional
    @Query("update Rental set rIsExtReq = case when rIsExtReq = true then false else true end where uId = :uId and bNo = :bNo and rIsReturned = false")
    void updateExtReq(@Param("uId") String uId, @Param("bNo") int bNo);

    // Old rentalList Query
    /*@Query("select b.bNo, b.bTitle, b.bAuthor, r.rRentalDate, r.rWhenToReturn, r.rIsExtended, r.rIsReturnReq from Rental r join Book b on r.bNo = b.bNo where r.uId = :uId and r.rIsReturned = false")
    List<Object[]> rentalList(@Param("uId") String uId);*/

    /*@Query("select b.bNo, b.bTitle, b.bAuthor, r.rRentalDate, r.rWhenToReturn, r.rIsExtended, r.rIsReturnReq, rr.rReqDate " +
            "from Rental r " +
            "join Book b on r.bNo = b.bNo " +
            "left join ReturnRequest rr on r.bNo = rr.bNo and r.uId = rr.uId and rr.rRtnReq = true " +
            "where r.uId = :uId and r.rIsReturned = false")
    List<Object[]> rentalList(@Param("uId") String uId);*/

    @Query("select b.bNo, b.bTitle, b.bAuthor, r.rRentalDate, r.rWhenToReturn, r.rIsExtended, r.rIsReturnReq, r.rIsExtReq, " +
            "rr.rReqDate as rRtnReqDate, er.rReqDate as rExtReqDate " +
            "from Rental r " +
            "join Book b on r.bNo = b.bNo " +
            "left join ReturnRequest rr on r.bNo = rr.bNo and r.uId = rr.uId and rr.rRtnReq = true " +
            "left join ExtensionRequest er on r.bNo = er.bNo and r.uId = er.uId and er.rExtReq = true " +
            "where r.uId = :uId and r.rIsReturned = false")
    List<Object[]> rentalList(@Param("uId") String uId);

    @Query("select b.bNo, b.bTitle, b.bAuthor, r.rRentalDate, r.rWhenToReturn, r.rReturnDate, r.rIsExtended " +
            "from Rental r join Book b on r.bNo = b.bNo " +
            "where r.uId = :uId and r.rIsReturned = true")
    List<Object[]> historyList(@Param("uId") String uId);

//rental의 isExtReq 값을 0 으로, isExtended를 1로, when to return을 15일 뒤로

    @Modifying
    @Transactional
    @Query(value = "update Rental set r_is_ext_req = false, r_is_extended = true, r_when_to_return = r_when_to_return + INTERVAL 15 DAY where u_id= :uId and b_no = :bNo and r_is_returned = false", nativeQuery = true)
    void updateExtStatus(@Param("uId") String uId, @Param("bNo") Integer bNo);

    //rental에 returnDate=now, isReturnReq = false, isReturned = true로 업데이트
    @Modifying
    @Transactional
    @Query(value = "UPDATE Rental r " +
            "JOIN return_request rr ON r.b_no = rr.b_no AND r.u_id = rr.u_id " +
            "SET r.r_return_date = CURRENT_TIMESTAMP, " +
            "    r_is_return_req = false, " +
            "    r_is_returned = true, " +
            "    r_late_return = CASE WHEN rr.r_req_date > r.r_when_to_return THEN true ELSE r.r_late_return END " +
            "WHERE r.u_id = :uId AND r.b_no = :bNo AND r.r_is_returned = false AND rr.r_rtn_req = true",
            nativeQuery = true)
    void updateRtnStatus(@Param("uId") String uId, @Param("bNo") Integer bNo);

    @Query("select count(*) from Rental")
    int findAllCount();
}

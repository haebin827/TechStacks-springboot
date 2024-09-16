package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.RentalRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RenReqRepository extends JpaRepository<RentalRequest, Integer> {

    @Query("select count(*) from RentalRequest rr where rr.bNo = :bNo and rr.uId = :uId and rr.rRenReq = true")
    int requestCheck(@Param("uId") String uId, @Param("bNo") int bNo);

    @Query("select count(*) from RentalRequest where uId = :uId and rRenReq = true")
    int findReqCount(@Param("uId") String uId);

    long countByrRenReqTrue();

    @Modifying
    @Transactional
    @Query("delete RentalRequest where uId = :uId and rRenReq = true")
    void deleteIncompleteList(String uId);

    @Query("select b.bTitle, b.bAuthor, r.rReqDate, b.bNo from RentalRequest r join Book b on r.bNo = b.bNo where r.uId = :uId and r.rRenReq = true")
    List<Object[]> currReqList(String uId);

    @Query("select b.bTitle, b.bAuthor, b.bNo, r.uId, r.rReqDate, b.bIsRental, " +
            "(select count(*) from Rental rental where rental.uId = r.uId and rental.rIsReturned = false) as rentalCount, " +
            "u.uNo, u.uIsBlacklist " +  // Select uNo and uIsBlacklist from User table
            "from RentalRequest r " +
            "join Book b on r.bNo = b.bNo " +
            "join User u on r.uId = u.uId " +  // Join with User table
            "where r.rRenReq = true and b.bIsActive = true")
    List<Object[]> currAllReqList();

    @Modifying
    @Transactional
    @Query("update RentalRequest set rRenReq = false where uId = :uId and bNo = :bNo and rRenReq = true")
    void updateRenReq(@Param("uId") String uId, @Param("bNo") Integer bNo);

    @Modifying
    @Transactional
    @Query("delete RentalRequest where uId = :uId and bNo = :bNo and rRenReq = true")
    void deleteRenReq(@Param("uId") String uId, @Param("bNo") int bNo);
}

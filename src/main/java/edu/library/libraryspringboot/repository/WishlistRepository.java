package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.domain.Wishlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    @Query("select count(*) from Wishlist w where w.uId = :uId and w.bNo = :bNo")
    int findWListCnt(@Param("uId") String uId, @Param("bNo") int bNo);

    @Modifying
    @Transactional
    @Query("delete Wishlist where uId = :uId and bNo = :bNo")
    void deleteWList(@Param("uId") String uId, @Param("bNo") int bNo);
}

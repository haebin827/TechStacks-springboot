package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.repository.search.BookSearch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer>, BookSearch {

    @Query(value="select now()", nativeQuery = true)
    String getTime();

    @Modifying
    @Transactional
    @Query("update Book set bIsActive = false where bNo = :bNo")
    void updateActiveStatus(int bNo);

    @Modifying
    @Transactional
    @Query("update Book set bIsRental = true where bNo = :bNo")
    void updateRenStatus(int bNo);

    @Modifying
    @Transactional
    @Query("update Book set bIsRental = false, bCondition = :bCondition where bNo = :bNo")
    void updateRtnStatus(@Param("bNo") int bNo, @Param("bCondition") String bCondition);
}

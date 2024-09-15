package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Category;
import edu.library.libraryspringboot.repository.search.BookSearch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    /*@Modifying
    @Transactional
    @Query("delete from Book b where b.bCategory like :cCode1%")
    void deleteBooksByMainCat(String cCode1);*/

    /*@Modifying
    @Transactional
    @Query("delete from Book b where b.bCategory = :cDcode")
    void deleteBooksBySubCat(String cDcode);*/

    @Query("select b from Book b where b.bCategory like :cCode1%")
    List<Book> findMainCatBooklist(String cCode1);

    @Query("select b from Book b where b.bCategory = :cDcode")
    List<Book> findSubCatBooklist(String cDcode);

    @Modifying
    @Transactional
    @Query("update Book set bCategory = :bCat where bCategory = :bPrevCat")
    void changeCat(@Param("bCat") String bCat, @Param("bPrevCat") String bPrevCat);
}

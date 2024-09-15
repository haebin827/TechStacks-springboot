package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Transactional
    @Query("update Category set cName = :cName where cId = :cId")
    void updateCatName(@Param("cName") String cName, @Param("cId") int cId);

    @Modifying
    @Transactional
    @Query("delete Category where cCode1 = :cCode1")
    void deleteMainCatList(String cCode1);

    @Modifying
    @Transactional
    @Query("delete Category where cDcode = :cDcode")
    void deleteSubCatList(String cDcode);

    // 가장 큰 cCode1 값을 구하는 쿼리
    @Query("select max(c.cCode1) from Category c")
    String findMaxCCode1();

    @Query("SELECT c.cDcode FROM Category c WHERE c.cCode1 = :selectedMainCat AND c.cCode2 = (SELECT MAX(c2.cCode2) FROM Category c2 WHERE c2.cCode1 = :selectedMainCat)")
    String findMaxCDcode(String selectedMainCat);
}

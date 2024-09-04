package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.domain.FaqCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaqCatRepository extends JpaRepository<FaqCategory, Integer>  {

    @Modifying
    @Transactional
    @Query("update FaqCategory set fName = :fName where fNo = :fNo")
    void updateCatName(@Param("fName") String fName, @Param("fNo") int fNo);
}

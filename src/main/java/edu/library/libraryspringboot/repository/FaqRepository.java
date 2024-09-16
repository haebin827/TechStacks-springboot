package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.repository.search.FaqSearch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaqRepository extends JpaRepository<Faq, Integer>, FaqSearch {

    @Transactional
    void deleteByfCategory(int fCategory);

    @Modifying
    @Transactional
    @Query("update Faq set fCategory = :fCat where fCategory = :fPrevCat")
    void changeCat(@Param("fCat") int fCat, @Param("fPrevCat") int fPrevCat);
}

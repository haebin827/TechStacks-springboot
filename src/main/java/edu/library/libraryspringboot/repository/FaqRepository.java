package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.repository.search.FaqSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqRepository extends JpaRepository<Faq, Integer>, FaqSearch {

    @Query(value="select now()", nativeQuery = true)
    String getTime();
}

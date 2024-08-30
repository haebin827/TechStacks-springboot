package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.domain.FaqCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqCatRepository extends JpaRepository<FaqCategory, Integer>  {
}

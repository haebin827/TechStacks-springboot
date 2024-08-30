package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.DeletedBook;
import edu.library.libraryspringboot.repository.search.DelBookSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelBookRepository extends JpaRepository<DeletedBook, Integer>, DelBookSearch {
}

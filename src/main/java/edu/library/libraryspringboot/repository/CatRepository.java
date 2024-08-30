package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Category, Integer> {
}

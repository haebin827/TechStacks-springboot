package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r where r.book.bNo = :bNo")
    Page<Review> listOfBook(@Param("bNo")Integer bNo, Pageable pageable);
}

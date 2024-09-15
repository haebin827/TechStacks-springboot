/*
package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository rr;

    @Test
    public void testInsert() {
        Integer bNo = 7658;

        Book book = Book.builder().bNo(bNo).build();

        Review review = Review.builder()
                .book(book)
                .rReviewText("TEST REPO")
                .rId("1234")
                .build();

        rr.save(review);
    }

    @Transactional
    @Test
    public void testBookReviews() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rNo").descending());
        Page<Review> result = rr.listOfBook(7658, pageable);

        result.getContent().forEach(review -> {
            log.info(review);
        });
    }
}
*/

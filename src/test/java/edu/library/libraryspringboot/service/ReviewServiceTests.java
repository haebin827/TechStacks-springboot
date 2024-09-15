/*
package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Review;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReviewServiceTests {

    @Autowired
    private ReviewService rs;

    @Test
    public void testRegister() {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rReviewText("TEST SERVICE")
                .rId("1234")
                .bNo(7658)
                .build();

        rs.register(reviewDTO);
    }


}
*/

/*
package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Review;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.ReviewDTO;
import edu.library.libraryspringboot.repository.BookRepository;
import edu.library.libraryspringboot.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository rr;
    private final BookRepository br;
    private final ModelMapper mm;

    */
/*@Override
    public Integer register(ReviewDTO reviewDTO) {
        Review review = mm.map(reviewDTO, Review.class);
        review.setBook(reviewDTO.toBook());

        Integer rNo = rr.save(review).getRNo();

        return rNo;
    }*//*


    @Override
    public Integer register(ReviewDTO reviewDTO) {
        if (reviewDTO.getBNo() == null) {
            throw new IllegalArgumentException("Book ID (bNo) must not be null");
        }

        Book book = br.findById(reviewDTO.getBNo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));

        Review review = mm.map(reviewDTO, Review.class);
        review.setBook(book);

        Integer rNo = rr.save(review).getRNo();

        return rNo;
    }



    @Override
    public ReviewDTO read(Integer rNo) {
        Optional<Review> reviewOptional = rr.findById(rNo);
        Review review = reviewOptional.orElseThrow();
        return mm.map(review, ReviewDTO.class);
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<Review> reviewOptional = rr.findById(reviewDTO.getRNo());
        Review review = reviewOptional.orElseThrow();
        review.changeReview(reviewDTO.getRReviewText());
        rr.save(review);
    }

    @Override
    public void remove(Integer rNo) {
        rr.deleteById(rNo);
    }

    @Override
    public PageResponseDTO<ReviewDTO> getListOfReviews(Integer bNo, PageRequestDTO pgReqDTO) {
        Pageable pageable = PageRequest.of(pgReqDTO.getPage() <= 0? 0:pgReqDTO.getPage()-1,
                pgReqDTO.getSize(), Sort.by("rNo").ascending());

        Page<Review> result = rr.listOfBook(bNo, pageable);

        */
/*List<ReviewDTO> dtoList = result.getContent().stream().map(review -> mm.map(review, ReviewDTO.class)).collect(Collectors.toList());*//*

        List<ReviewDTO> dtoList = result.getContent().stream().map(review -> {
            ReviewDTO reviewDTO = mm.map(review, ReviewDTO.class);
            reviewDTO.setBNo(review.getBook().getBNo()); // Book 객체의 bNo 값을 DTO에 설정
            return reviewDTO;
        }).collect(Collectors.toList());

        return PageResponseDTO.<ReviewDTO>withAll()
                .pageRequestDTO(pgReqDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
*/

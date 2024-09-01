package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Review", indexes = {
        @Index(name = "idx_review_book_bNo", columnList = "book_bNo")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "book")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rNo;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    private String rReviewText;

    private String rId;

    private LocalDateTime rRegDate, mModDate;

    @PrePersist
    public void onCreate() {
        rRegDate = LocalDateTime.now();
    }

    public void changeReview(String rReviewText) {
        this.rReviewText = rReviewText;
    }

}

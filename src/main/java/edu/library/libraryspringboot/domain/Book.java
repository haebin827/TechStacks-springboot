package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bNo;

    @Column(nullable = false, length = 1000)
    private String bTitle;

    @Column(length = 1000)
    private String bAuthor;

    @Column(nullable = false, length = 10)
    private String bIsbn;

    @Column(length = 4)
    private Integer bYear;

    @Column(length = 255)
    private String bPublisher;

    @Column(length = 4)
    private String bCategory;

    @Column(nullable = false)
    private Boolean bIsRental;

    @Column(nullable = false)
    private Boolean bIsActive;

    @Column(nullable = false, length = 20)
    private String bCondition;

    @Column
    private String bCover;

    @Column
    private LocalDateTime bRegDate;

    @PrePersist
    private void onCreate() {
        bIsRental = false;
        bIsActive = true;
        bCondition = "Excellent";
        bCover = null;
        bRegDate = LocalDateTime.now();
    }

    public void change(String bTitle, String bAuthor, String bIsbn, Integer bYear, String bPublisher, String bCategory, String bCondition) {
        this.bTitle = bTitle;
        this.bAuthor = bAuthor;
        this.bIsbn = bIsbn;
        this.bYear = bYear;
        this.bPublisher = bPublisher;
        this.bCategory = bCategory;
        this.bCondition = bCondition;
    }
}

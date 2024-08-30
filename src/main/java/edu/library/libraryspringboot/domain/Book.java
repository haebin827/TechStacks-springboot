package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, length = 255)
    private String bTitle;

    @Column(length = 255)
    private String bAuthor;

    @Column(nullable = false, length = 10)
    private String bIsbn;

    @Column(length = 4)
    private Integer bYear;

    @Column(length = 255)
    private String bPublisher;

    @Column(length = 255)
    private String bCategory;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean bIsRental;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean bIsActive;

    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'EXCELLENT'")
    private String bCondition;

    @Column
    private String bCover;

    @PrePersist
    private void prePersist() {
        bIsRental = false;
        bIsActive = true;
        bCondition = "excellent";
        bCover = null;
    }

    public void change(String bTitle, String bAuthor, String bIsbn, Integer bYear, String bPublisher) {
        this.bTitle = bTitle;
        this.bAuthor = bAuthor;
        this.bIsbn = bIsbn;
        this.bYear = bYear;
        this.bPublisher = bPublisher;
    }

}

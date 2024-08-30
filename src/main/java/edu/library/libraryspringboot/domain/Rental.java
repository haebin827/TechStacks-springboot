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
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rId;

    @Column(length = 15, nullable = false)
    private String uId;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer bNo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rRentalDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime rReturnDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime rWhenToReturn;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean rIsExtended;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean rIsReturned;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean rIsReturnReq;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean rIsExtReq;

    @Column
    private Boolean rLateReturn;

    @PrePersist
    private void prePersist() {
        rIsExtended = false;
        rIsReturned = false;
        rIsExtReq = false;
        rIsReturnReq = false;
        rRentalDate = LocalDateTime.now();
        rWhenToReturn = LocalDateTime.now().plusDays(30);
        rLateReturn = null;
    }

    public void change(String uId, int bNo) {
        this.uId = uId;
        this.bNo = bNo;
    }

    public void bookReturn() {
        this.rReturnDate = LocalDateTime.now();
    }
}

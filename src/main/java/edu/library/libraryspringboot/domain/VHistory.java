package edu.library.libraryspringboot.domain;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Subselect("select r.r_id as id, b.b_no, b.b_title, b.b_author, r.r_rental_date, r.r_when_to_return, r.r_return_date, r.r_is_extended, r.u_id, b.b_isbn, b.b_is_active, r.r_late_return " +
        "from Rental r join Book b on r.b_no = b.b_no " +
        "where r.r_is_returned= true")


@Table(name = "VHistory")
public class VHistory {

    @Id
    private Integer id; // 고유 식별자 필드

    @Column(name = "b_no")
    private Integer bNo;

    @Column(name = "u_id")
    private String uId;

    @Column(name = "b_isbn")
    private String bIsbn;

    @Column(name = "b_is_active")
    private Boolean bIsActive;

    @Column(name = "b_title", length = 255)
    private String bTitle;

    @Column(name = "b_author", length = 255)
    private String bAuthor;

    @Column(name = "r_rental_date")
    private LocalDateTime rRentalDate;

    @Column(name = "r_when_to_return")
    private LocalDateTime rWhenToReturn;

    @Column(name = "r_return_date")
    private LocalDateTime rReturnDate;

    @Column(name = "r_is_extended")
    private Boolean rIsExtended;

    @Column(name = "r_late_return")
    private Boolean rLateReturn;
}

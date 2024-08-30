package edu.library.libraryspringboot.domain;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Subselect("select r.r_id as id, b.b_title, b.b_author, b.b_no, r.u_id, b.b_is_rental, u.u_name, " +
        "u.u_no, u.u_is_blacklist, r.r_rental_date, r.r_is_extended, r.r_when_to_return, r.r_return_date, r.r_is_returned " +  // Select uNo and uIsBlacklist from User table
        "from rental r " +
        "join Book b on r.b_no = b.b_no " +
        "join User u on r.u_id = u.u_id")
@Table(name = "VAdminHistory")
public class VAdminHistory {

    @Id
    private Integer id;

    @Column(name = "b_title")
    private String bTitle;

    @Column(name = "b_author")
    private String bAuthor;

    @Column(name = "b_no")
    private Integer bNo;

    @Column(name = "u_name")
    private String uName;

    @Column(name = "u_id")
    private String uId;

    @Column(name = "b_is_rental")
    private Boolean bIsRental;

    @Column(name = "u_no")
    private Integer uNo;

    @Column(name = "u_is_blacklist")
    private Boolean uIsBlacklist;

    @Column(name = "r_rental_date")
    private LocalDateTime rRentalDate;

    @Column(name = "r_return_date")
    private LocalDateTime rReturnDate;

    @Column(name = "r_is_extended")
    private Boolean rIsExtended;

    @Column(name = "r_when_to_return")
    private LocalDateTime rWhenToReturn;

    @Column(name = "r_is_returned")
    private Boolean rIsReturned;
}

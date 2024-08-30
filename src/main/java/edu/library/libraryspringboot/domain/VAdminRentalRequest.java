package edu.library.libraryspringboot.domain;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Subselect("select r.r_id as id, b.b_title, b.b_author, b.b_no, r.u_id, r.r_req_date, b.b_is_rental, " +
        "(select count(*) from Rental rental where rental.u_id = r.u_id and rental.r_is_returned = false) as rental_count, " +
        "u.u_no, u.u_is_blacklist " +  // Select uNo and uIsBlacklist from User table
        "from rental_request r " +
        "join Book b on r.b_no = b.b_no " +
        "join User u on r.u_id = u.u_id " +  // Join with User table
        "where r.r_ren_req = true and b.b_is_active = true")
@Table(name = "VAdminRentalRequest")
public class VAdminRentalRequest {

    @Id
    private int id;  // rental_request 테이블의 r_id

    @Column(name = "b_title")
    private String bTitle;  // Book 테이블의 b_title

    @Column(name = "b_author")
    private String bAuthor;  // Book 테이블의 b_author

    @Column(name = "b_no")
    private Integer bNo;  // Book 테이블의 b_no

    @Column(name = "u_id")
    private String uId;  // User 테이블의 u_id

    @Column(name = "r_req_date")
    private LocalDateTime rReqDate;  // rental_request 테이블의 r_req_date

    @Column(name = "b_is_rental")
    private Boolean bIsRental;  // Book 테이블의 b_is_rental

    @Column(name = "rental_count")
    private Integer rentalCount;  // 사용자가 현재 대여 중인 책 수

    @Column(name = "u_no")
    private Integer uNo;  // User 테이블의 u_no

    @Column(name = "u_is_blacklist")
    private Boolean uIsBlacklist;  // 사용자의 블랙리스트 여부
}

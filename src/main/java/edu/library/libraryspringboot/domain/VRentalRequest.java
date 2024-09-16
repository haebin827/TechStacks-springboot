package edu.library.libraryspringboot.domain;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Subselect("select r.r_id as id, b.b_title as b_title, b.b_author as b_author, b.b_no as b_no, r.u_id as u_id, " +
        "r.r_req_date as r_req_date, b.b_is_rental as b_is_rental, " +
        "count(rental.u_id) as rental_count, " +
        "u.u_no as u_no, u.u_is_blacklist as u_is_blacklist " +
        "from rental_request r " +
        "join Book b on r.b_no = b.b_no " +
        "join User u on r.u_id = u.u_id " +
        "left join Rental rental on rental.u_id = r.u_id and rental.r_is_returned = false " +
        "where r.r_ren_req = true and b.b_is_active = true " +
        "group by r.r_id, b.b_title, b.b_author, b.b_no, r.u_id, r.r_req_date, b.b_is_rental, u.u_no, u.u_is_blacklist")


@Table(name = "VRentalRequest")
public class VRentalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 식별자 필드 추가

    @Column(name = "b_title")
    private String bTitle;

    @Column(name = "b_author")
    private String bAuthor;

    @Column(name = "b_no")
    private Integer bNo;

    @Column(name = "u_id")
    private String uId;

    @Column(name = "r_req_date")
    private LocalDateTime rReqDate;

    @Column(name = "b_is_rental")
    private Boolean bIsRental;

    @Column(name = "rental_count")
    private Integer rentalCount;

    @Column(name = "u_no")
    private Integer uNo;

    @Column(name = "u_is_blacklist")
    private Boolean uIsBlacklist;
}

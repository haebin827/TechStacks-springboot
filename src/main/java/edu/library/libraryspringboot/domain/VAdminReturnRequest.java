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
@Subselect("select r.r_id as id, b.b_title, b.b_author, b.b_no, rr.u_id, rr.r_req_date, u.u_no, u.u_is_blacklist, " +
        "r.r_when_to_return, b.b_condition, u.u_level " +
        "from return_request rr " +
        "join Book b on rr.b_no = b.b_no " +
        "join User u on rr.u_id = u.u_id " +
        "join Rental r on rr.b_no = r.b_no and rr.u_id = r.u_id " +
        "where rr.r_rtn_req = true and b.b_is_active = true and b.b_is_rental = true and r.r_is_return_req = true")
@Table(name = "VAdminReturnRequest")
public class VAdminReturnRequest {

    @Id
    private int id;  // Return request ID, from the rental table (r_id)

    @Column(name = "b_title")
    private String bTitle;  // Book title

    @Column(name = "b_author")
    private String bAuthor;  // Book author

    @Column(name = "b_no")
    private Integer bNo;  // Book number (primary key for Book)

    @Column(name = "u_id")
    private String uId;  // User ID

    @Column(name = "r_req_date")
    private LocalDateTime rReqDate;  // Date of return request

    @Column(name = "u_no")
    private Integer uNo;  // User number (primary key for User)

    @Column(name = "u_is_blacklist")
    private Boolean uIsBlacklist;  // Whether the user is blacklisted

    @Column(name = "r_when_to_return")
    private LocalDateTime rWhenToReturn;  // The due date for the book return

    @Column(name = "b_condition")
    private String bCondition;  // The condition of the book

    @Column(name = "u_level")
    private String uLevel;
}

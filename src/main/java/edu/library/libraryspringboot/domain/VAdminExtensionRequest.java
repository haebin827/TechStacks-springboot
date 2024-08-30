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
@Subselect("select e.e_id as id, b.b_title, b.b_author, b.b_no, e.u_id, e.r_req_date, u.u_no, u.u_is_blacklist " +
        "from extension_request e " +
        "join Book b on e.b_no = b.b_no " +
        "join User u on e.u_id = u.u_id " +
        "where e.r_ext_req = true and b.b_is_active = true and b.b_is_rental = true")
@Table(name = "VAdminExtensionRequest")
public class VAdminExtensionRequest {

    @Id
    private Integer id;

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

    @Column(name = "u_no")
    private Integer uNo;

    @Column(name = "u_is_blacklist")
    private Boolean uIsBlacklist;
}

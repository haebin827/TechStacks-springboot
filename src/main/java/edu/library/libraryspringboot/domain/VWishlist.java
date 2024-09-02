package edu.library.libraryspringboot.domain;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Getter
@Subselect("SELECT " +
        "    w.w_id AS id, " +
        "    w.u_id, " +
        "    w.b_no, " +
        "    b.b_title, " +
        "    b.b_author " +
        "FROM " +
        "    Wishlist w " +
        "JOIN Book b ON w.b_no = b.b_no")
@Table(name = "VWishlist")
public class VWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "u_id")
    private String uId;

    @Column(name = "b_no")
    private Integer bNo;

    @Column(name = "b_title")
    private String bTitle;

    @Column(name = "b_author")
    private String bAuthor;
}

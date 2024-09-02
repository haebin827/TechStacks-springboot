package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wId;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column(nullable = false)
    private Integer bNo;
}

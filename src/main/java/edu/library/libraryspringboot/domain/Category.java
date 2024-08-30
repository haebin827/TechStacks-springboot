package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;

    @Column(nullable = false, length = 255)
    private String cName;

    @Column(nullable = false, length = 2)
    private String cCode1;

    @Column(length = 2)
    private String cCode2;

    @Column(nullable = false, length = 4)
    private String cDcode;

    public void change(String cName, String cCode1, String cCode2, String cDcode) {
        this.cName = cName;
        this.cCode1 = cCode1;
        this.cCode2 = cCode2;
        this.cDcode = cDcode;
    }
}

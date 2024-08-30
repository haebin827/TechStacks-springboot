package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fId;

    @Column(nullable = false, length = 255)
    private String fQuestion;

    @Column(nullable = false, length = 1000)
    private String fAnswer;

    @Column(nullable = false)
    private String fCategory;

    public void change(String fQuestion, String fAnswer, String fCategory) {
        this.fQuestion = fQuestion;
        this.fAnswer = fAnswer;
        this.fCategory = fCategory;
    }
}

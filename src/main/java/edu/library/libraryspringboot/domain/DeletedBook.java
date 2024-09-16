package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeletedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dId;

    @Column(nullable = false, length = 255)
    private String bTitle;

    @Column
    private LocalDateTime dDelDate;

    @Column(nullable = false, length = 255)
    private String dReason;

    @PrePersist
    protected void onCreate() {
        this.dDelDate = LocalDateTime.now();
    }
}

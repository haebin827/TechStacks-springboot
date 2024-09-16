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
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blId;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column(length = 255)
    private String blReason;

    @Column
    private LocalDateTime blRegDate;

    @PrePersist
    private void onCreate() {
        blRegDate = LocalDateTime.now();
    }

    public void change(String uId, String blReason) {
        this.uId = uId;
        this.blReason = blReason;
    }
}

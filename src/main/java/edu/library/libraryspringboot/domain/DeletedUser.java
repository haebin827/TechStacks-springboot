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
public class DeletedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dId;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column
    private LocalDateTime dDelDate;

    // If deleted by the user: BY USER
    // If deleted by the admin: BY ADMIN
    @Column(nullable = false, length = 255)
    private String dReason;

    @PrePersist
    protected void onCreate() {
        this.dDelDate = LocalDateTime.now();
    }

    public void change(String uId, String dReason) {
        this.uId = uId;
        this.dReason = dReason;
    }
}

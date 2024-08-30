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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dDelDate;

    // If user deleted: BY USER
    // If admin deleted: BY ADMIN
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

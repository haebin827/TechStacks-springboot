package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    private Integer blNo;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column(length = 255)
    private String blReason;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime blRegDate;

    @PrePersist
    private void onCreate() {
        blRegDate = LocalDateTime.now();
    }

    // Boolean을 통해 endDate 설정
    public void change(String uId, String blReason) {
        this.uId = uId;
        this.blReason = blReason;
    }

}

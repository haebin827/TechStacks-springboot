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
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rId;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column(nullable = false)
    private Integer bNo;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean rRtnReq;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rReqDate;

    @PrePersist
    public void onCreate() {
        rRtnReq = true;
        rReqDate = LocalDateTime.now();
    }

    /*public void change(LocalDateTime rWhenToRtn) {
        this.rWhenToRtn = rWhenToRtn;
    }*/
}

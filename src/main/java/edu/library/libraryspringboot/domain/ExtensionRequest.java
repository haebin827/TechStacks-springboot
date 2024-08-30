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
public class ExtensionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eId;

    @Column(nullable = false, length = 15)
    private String uId;

    @Column(nullable = false)
    private Integer bNo;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean rExtReq;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime rReqDate;

    @PrePersist
    public void onCreate() {
        rExtReq = true;
        rReqDate = LocalDateTime.now();
    }

    /*public void change(String uId, Integer bNo, Boolean rExtReq) {
        this.uId = uId;
        this.bNo = bNo;
        this.rExtReq = rExtReq;
    }*/
}

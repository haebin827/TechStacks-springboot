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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uNo;

    @Column(nullable = false, unique = true, length = 15)
    private String uId;

    @Column(nullable = false, length = 255)
    private String uPw;

    @Column(nullable = false, length = 20)
    private String uName;

    @Column(nullable = false, unique = true, length = 10)
    private String uPhone;

    @Column(nullable = false, length = 15, columnDefinition = "VARCHAR(15) DEFAULT 'BRONZE'")
    private String uLevel;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean uIsLevelUp;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer uPoint;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer uNoOfLateReturn;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean uIsBlacklist;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime uRegDate;

    @Column(length = 50, columnDefinition = "VARCHAR(50) DEFAULT NULL")
    private String uUuid;

    @PrePersist
    private void prePersist() {
        uIsBlacklist = false;
        uLevel = "BRONZE";
        uPoint = 0;
        uNoOfLateReturn = 0;
        uRegDate = LocalDateTime.now();
        uIsLevelUp = false;
    }

    public void change(String uId, String uPw, String uName, String uPhone, Boolean uIsBlacklist) {
        this.uId = uId;
        this.uPw = uPw;
        this.uName = uName;
        this.uPhone = uPhone;
        this.uIsBlacklist = uIsBlacklist;
    }

}
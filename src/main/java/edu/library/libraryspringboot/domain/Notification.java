package edu.library.libraryspringboot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nNo;

    @Column(length = 100, nullable = false)
    private String nTitle;

    @Column(length = 1000, nullable = false)
    private String nContent;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean nIsImp;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer nViews;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime nRegDate;

    @LastModifiedDate
    @Column(insertable = false, columnDefinition = "TIMESTAMP NULL")
    private LocalDateTime nModDate;


    @PrePersist
    protected void onCreate() {
        nRegDate = LocalDateTime.now();
        nViews = 0;
    }

    public void change(String nTitle, String nContent, Boolean nIsImp) {
        this.nTitle = nTitle;
        this.nContent = nContent;
        this.nIsImp = nIsImp;
        this.nModDate = LocalDateTime.now();
    }
}

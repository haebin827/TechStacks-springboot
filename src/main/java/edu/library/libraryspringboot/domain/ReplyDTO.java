package edu.library.libraryspringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Integer rNo;
    private Integer nNo;
    private String replyTest;
    private String replyer;
    private LocalDateTime regDate, modDate;
}

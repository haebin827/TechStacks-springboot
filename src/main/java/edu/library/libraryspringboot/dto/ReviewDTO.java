/*
package edu.library.libraryspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.library.libraryspringboot.domain.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Integer rNo;

    private Integer bNo;

    private String rReviewText;
    private String rId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rRegDate, mModDate;

    // 추가: DTO에서 Book 객체를 생성하는 메서드
    public Book toBook() {
        return Book.builder().bNo(this.bNo).build();
    }


}
*/

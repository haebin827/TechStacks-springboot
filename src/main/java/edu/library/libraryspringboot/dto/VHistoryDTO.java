package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VHistoryDTO {

    private Integer id;

    private Integer bNo;

    private String uId;

    private String bIsbn;

    private Boolean bIsActive;

    private String bTitle;

    private String bAuthor;

    private LocalDateTime rRentalDate;

    private LocalDateTime rWhenToReturn;

    private LocalDateTime rReturnDate;

    private Boolean rIsExtended;

    private Boolean rLateReturn;
}

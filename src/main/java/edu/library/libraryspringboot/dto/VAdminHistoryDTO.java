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
public class VAdminHistoryDTO {

    private Integer id;
    private String bTitle;
    private String bAuthor;
    private Integer bNo;
    private String uName;
    private String uId;
    private Boolean bIsRental;
    private Integer uNo;
    private Boolean uIsBlacklist;
    private LocalDateTime rRentalDate;
    private LocalDateTime rReturnDate;
    private Boolean rIsExtended;
    private LocalDateTime rWhenToReturn;
    private Boolean rIsReturned;
}

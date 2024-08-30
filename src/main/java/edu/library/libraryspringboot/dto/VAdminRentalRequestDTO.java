package edu.library.libraryspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VAdminRentalRequestDTO {

    private int id;
    private String bTitle;
    private String bAuthor;
    private Integer bNo;
    private String uId;
    private LocalDateTime rReqDate;
    private Boolean bIsRental;
    private Integer rentalCount;
    private Integer uNo;
    private Boolean uIsBlacklist;
}

package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class RentalDTO {

    private Integer rId;

    @NotEmpty
    private String uId;

    @NotNull
    private Integer bNo;

    private LocalDateTime rRentalDate;
    private LocalDateTime rReturnDate;
    private LocalDateTime rWhenToReturn;
    private Boolean rIsExtended;
    private Boolean rIsReturned;
    private Boolean rIsExtReq;
    private Boolean rIsReturnReq;
    private Boolean rLateReturn;
}

package edu.library.libraryspringboot.dto;

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
public class RentalRequestDTO {

    private Integer rId;

    @NotEmpty
    private String uId;

    @NotNull
    private Integer bNo;

    private Boolean rRenReq;

    private LocalDateTime rReqDate;
}

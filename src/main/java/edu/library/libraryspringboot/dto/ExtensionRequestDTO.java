package edu.library.libraryspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionRequestDTO {

    private Integer eId;

    @NotEmpty
    private String uId;

    @NotEmpty
    private Integer bNo;

    private Boolean rExtReq;

    private LocalDateTime rReqDate;
}

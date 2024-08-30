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
public class DeletedBookDTO {

    private Integer dId;

    @NotEmpty
    private String bTitle;
    private String dReason;

    private LocalDateTime dDelDate;
}

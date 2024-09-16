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
public class DeletedUserDTO {

    private Integer dId;                // int AI PK

    @NotEmpty
    private String uId;                 // varchar(15) NN

    @NotEmpty
    private String dReason;             // varchar(255) NN

    private LocalDateTime dDelDate;     // TIMESTAMP default CURRENT_TIMESTAMP

}

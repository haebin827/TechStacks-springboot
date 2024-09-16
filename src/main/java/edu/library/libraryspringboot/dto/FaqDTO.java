package edu.library.libraryspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaqDTO {

    private Integer fId;        // int NN AI

    @NotEmpty
    private String fQuestion;   // varchar(255) NN

    @NotEmpty
    private String fAnswer;     // varchar(1000) NN

    private Integer fCategory;
}

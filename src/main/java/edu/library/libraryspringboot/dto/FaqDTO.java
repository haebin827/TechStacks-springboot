package edu.library.libraryspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String fAnswer;     // varchar(1000) NN

    private String fCategory;
}

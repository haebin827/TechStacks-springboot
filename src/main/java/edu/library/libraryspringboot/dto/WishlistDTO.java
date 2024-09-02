package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
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
public class WishlistDTO {

    private Integer wId;

    @NotEmpty
    private String uId;

    @NotNull
    private Integer bNo;
}

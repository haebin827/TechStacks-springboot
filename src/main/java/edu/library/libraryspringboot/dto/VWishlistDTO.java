package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VWishlistDTO {

    private Integer id;
    private String uId;
    private Integer bNo;
    private String bTitle;
    private String bAuthor;
}

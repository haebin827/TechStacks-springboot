package edu.library.libraryspringboot.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Integer bNo;       // int AI PK

    @NotEmpty
    private String bIsbn;      // varchar(10) NN

    @NotEmpty
    private String bTitle;     // varchar(255) NN

    private String bAuthor;    // varchar(255) default NULL

    //@Size(min = 1000, max = 3000)
    private Integer bYear;     // int default NULL

    private String bPublisher; // varchar(255) default NULL

    private String bCategory;  // varchar(255) default NULL

    @NotNull
    private boolean bIsRental;   // tinyint(1) NN default '0'

    @NotNull
    private boolean bIsActive;   // tinyint(1) NN default '1'

    private String bCondition;

    private String bCover;
}

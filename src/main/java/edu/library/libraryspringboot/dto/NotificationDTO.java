package edu.library.libraryspringboot.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Integer nNo;       // int AI PK

    @NotEmpty
    private String nTitle;     // varchar(100) NN

    @NotEmpty
    private String nContent;   // varchar(1000) NN

    @NotNull
    private Boolean nIsImp;   // tinyint(1) NN default '0'

    private Integer nViews;

    private LocalDateTime nRegDate; // timestamp NN default CURRENT_TIMESTAMP
    private LocalDateTime nModDate; // timestamp default null on update CURRENT_TIMESTAMP
}

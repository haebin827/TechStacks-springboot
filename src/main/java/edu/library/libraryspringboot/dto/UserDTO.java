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
public class UserDTO {

    private Integer uNo;                // int NN AI

    private String uId;                 // varchar(15) NN
    private String uPw;                 // varchar(255) NN
    private String uName;               // varchar(20) NN
    private String uPhone;              // varchar(10) NN
    private String uLevel;              // varchar(15) NN default 'BRONZE'
    private Boolean uIsLevelUp;
    private Integer uPoint;             // int NN default '0'
    private Integer uNoOfLateReturn;    // int NN default '0'
    private LocalDateTime uRegDate;     // timestamp N default CURRENT_TIMESTAMP

    private Boolean uIsBlacklist;       // tinyint(1) NN default '0'

    private String uUuid;
}
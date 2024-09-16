package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VAdminExtensionRequestDTO {

    private Integer id;

    private String bTitle;

    private String bAuthor;

    private Integer bNo;

    private String uId;

    private LocalDateTime rReqDate;

    private Integer uNo;

    private Boolean uIsBlacklist;
}

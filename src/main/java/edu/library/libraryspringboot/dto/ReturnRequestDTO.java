package edu.library.libraryspringboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRequestDTO {

    private Integer rId;

    @NotEmpty
    private String uId;
    private Integer bNo;

    private Boolean rRtnReq;
    private LocalDateTime rReqDate;
    //private LocalDateTime rWhenToRtn;
}

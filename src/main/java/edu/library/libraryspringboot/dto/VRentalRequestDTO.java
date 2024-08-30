package edu.library.libraryspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VRentalRequestDTO {

    private Integer id;

    private String b_title;

    private String b_author;

    private Integer b_no;

    private String u_id;

    private LocalDateTime r_req_date;

    private Boolean b_is_rental;

    private Integer rental_count;

    private Integer u_no;

    private Boolean u_is_blacklist;
}

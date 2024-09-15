/*
package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.ReviewDTO;
import edu.library.libraryspringboot.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation; // Import updated
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService rs;

    @Operation(summary = "Review POST", description = "Review POST") // Updated annotation
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> register(@Valid @RequestBody ReviewDTO reviewDTO, BindingResult bindingResult) throws BindException {

        log.info("reviewDTO: " + reviewDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String, Integer> resultMap = new HashMap<>();

        int rNo = rs.register(reviewDTO);
        resultMap.put("rNo", rNo);

        return resultMap;
    }

    @Operation(summary = "Review of Book", description = "Review GET")
    @GetMapping(value = "/list/{bNo}")
    public PageResponseDTO<ReviewDTO> getList(@PathVariable("bNo") Integer bNo, PageRequestDTO pgReqDTO) {
        PageResponseDTO<ReviewDTO> respDTO = rs.getListOfReviews(bNo, pgReqDTO);

        log.info("RESP DTO: " + respDTO);
        return respDTO;
    }

    @Operation(summary = "Read Review", description = "Read review GET")
    @GetMapping("/{rNo}")
    public ReviewDTO getReviewDTO(@PathVariable("rNo") Integer rNo) {
        ReviewDTO reviewDTO = rs.read(rNo);
        return reviewDTO;
    }

    @Operation(summary = "Delete Review", description = "Delete review")
    @DeleteMapping("/{rNo}")
    public Map<String, Integer> remove(@PathVariable("rNo") Integer rNo) {
        rs.remove(rNo);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("rNo", rNo);
        return resultMap;
    }

    @Operation(summary = "Modify Review", description = "Modify review")
    @PutMapping("/{rNo}")
    public Map<String, Integer> modify(@PathVariable("rNo") Integer rNo, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setRNo(rNo);

        //Text만 변경가능하게 엔티티에서 설정해둠!
        rs.modify(reviewDTO);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("rNo", rNo);
        return resultMap;
    }

}
*/

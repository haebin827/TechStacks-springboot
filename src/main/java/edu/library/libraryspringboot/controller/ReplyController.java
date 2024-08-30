package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.domain.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation; // Import updated
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    @Operation(summary = "Replies POST", description = "Posting replies") // Updated annotation
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Integer>> register(@RequestBody ReplyDTO replyDTO) {
        log.info("Reply" + replyDTO);
        Map<String, Integer> resultMap = Map.of("rNo", 111);
        return ResponseEntity.ok(resultMap);
    }

}

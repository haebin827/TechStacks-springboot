package edu.library.libraryspringboot.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class RtnReqRepositoryTests {

    @Autowired
    private RtnReqRepository rr;

    @Test
    public void testSelectRtnReqDate() {
        LocalDate date = rr.selectRtnReqDate(7631, "S538108");
        log.info("Rtn Req Date: " + date);
    }

}

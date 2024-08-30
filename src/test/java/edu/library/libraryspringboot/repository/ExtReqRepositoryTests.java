package edu.library.libraryspringboot.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ExtReqRepositoryTests {

    @Autowired
    private ExtReqRepository er;

    @Test
    public void testListAll() {
        log.info("list: " + er.currAllReqList());
    }
}

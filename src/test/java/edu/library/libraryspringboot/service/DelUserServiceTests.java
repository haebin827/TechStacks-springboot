package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.DeletedUserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class DelUserServiceTests {

    @Autowired
    private DelUserService ds;

    /*@Test
    public void testReadOneByUId() {
        DeletedUserDTO delUserDTO = ds.readOneByUId("ID91");
        log.info("Deleted User: " + delUserDTO);
    }*/
}

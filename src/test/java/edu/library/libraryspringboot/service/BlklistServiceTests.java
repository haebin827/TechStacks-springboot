package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BlacklistDTO;
import edu.library.libraryspringboot.dto.BookDTO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BlklistServiceTests {

    @Autowired
    private BlklistService bs;

    @Test
    public void testRegister() {
        log.info(bs.getClass().getName());

        BlacklistDTO blkDTO = BlacklistDTO.builder()
                .uId("UID")
                .blReason("REASON")
                .build();

        int blNo = bs.register(blkDTO);
        log.info("blNo: " + blNo);
    }

    /*@Test
    public void testModify() {

        BlacklistDTO blkDTO = BlacklistDTO.builder()
                .blNo(3)
                .uId("MODIFIED UID")
                .blReason("MODIFIED REASON")
                .blIsDone(true)
                .build();

        bs.modify(blkDTO);
        log.info("blkDTO: " + blkDTO);
    }*/

    /*@Test
    public void testReadOne() {
        log.info(bs.readOne(2));
    }*/

    @Test
    public void testRemove() {
        bs.remove(2);
    }

    @Test
    public void testGetAll() {
        log.info("BLACKLIST RECORD: " + bs.getAllByUId("UID"));
    }
}

package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.NotificationDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class NotifServiceTests {

    @Autowired
    private NotifService ns;

    @Test
    public void testRegister() {
        log.info(ns.getClass().getName());

        NotificationDTO notifDTO = NotificationDTO.builder()
                .nTitle("DEMO TITLE")
                .nContent("DEMO CONTENT")
                .nIsImp(true)
                .build();

        int bNo = ns.register(notifDTO);
        log.info("bNo: " + bNo);
    }

    @Test
    public void testModify() {

        NotificationDTO notifDTO = NotificationDTO.builder()
                .nNo(3)
                .nTitle("MODIFY TITLE2")
                .nContent("MODIFY CONTENT")
                .nIsImp(true)
                .build();

        ns.modify(notifDTO);
    }

    @Test
    public void testReadOne() {
        log.info(ns.readOne(2));
    }

    @Test
    public void testRemove() {
        ns.remove(4);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<NotificationDTO> responseDTO = ns.list(pageRequestDTO);
        log.info("responseDTO: " + responseDTO);
    }
}

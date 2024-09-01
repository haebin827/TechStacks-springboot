package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.FaqDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class FaqServiceTests {

    @Autowired
    private FaqService fs;

    /*@Test
    public void testRegister() {
        log.info(fs.getClass().getName());

        FaqDTO faqDTO = FaqDTO.builder()
                .fQuestion("new demo question")
                .fAnswer("new demo answer")
                .build();

        int fId = fs.register(faqDTO);
        log.info("fId: " + fId);
    }*/

    /*@Test
    public void testModify() {

        FaqDTO faqDTO = FaqDTO.builder()
                .fId(3)
                .fQuestion("new demo")
                .fAnswer("new demo")
                .build();

        fs.modify(faqDTO);
    }*/

    @Test
    public void testReadOne() {
        log.info(fs.readOne(2));
    }

    @Test
    public void testRemove() {
        fs.remove(4);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("q")
                .keyword("new")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<FaqDTO> responseDTO = fs.list(pageRequestDTO);
        log.info("responseDTO: " + responseDTO);
    }

    @Test
    public void testCatList() {
        log.info(fs.catList());
    }
}

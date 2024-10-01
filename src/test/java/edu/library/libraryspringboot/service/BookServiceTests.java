/*
package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BookServiceTests {

    @Autowired
    private BookService bs;

    */
/*@Test
    public void testRegister() {
        log.info(bs.getClass().getName());

        BookDTO bookDTO = BookDTO.builder()
                .bTitle("ADD TITLE")
                .bAuthor("ADD AUTHOR")
                .bIsbn("12345")
                .bYear(2024)
                .bPublisher("ADD PUBLISHER")
                .build();

        int bNo = bs.register(bookDTO);
        log.info("bNo: " + bNo);
    }
*//*

    */
/*@Test
    public void testModify() {

        BookDTO bookDTO = BookDTO.builder()
                .bNo(3)
                .bTitle("MODIFY2")
                .bIsbn("0000")
                .bAuthor("MODIFY AUTHOR")
                .build();

        bs.modify(bookDTO);
    }*//*


    @Test
    public void testReadOne() {
        log.info(bs.readOne(2));
    }

    */
/*@Test
    public void testRemove() {
        bs.remove(4);
    }*//*


    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("t")
                .keyword("data structure")
                .cat("0101")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BookDTO> responseDTO = bs.list(pageRequestDTO);
        log.info("responseDTO: " + responseDTO);
    }

    @Test
    public void testGetMaxCDcode() {
        log.info("DCode: " + bs.getMaxCDcode("02"));
    }

    @Test
    public void testGetMainCatBooklist() {
        log.info(bs.getMainCatBooklist("01"));
    }
}

*/

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Notification;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepo;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Book book = Book.builder()
                    .bTitle("title " + i)
                    .bAuthor("author " + i)
                    .bIsbn("" + i)
                    .bYear(i)
                    .bPublisher("publisher " + i)
                    .build();

            Book result = bookRepo.save(book);
            log.info("result: " + result);
        });
    }


    @Test
    public void testSelect() {
        Optional<Book> result = bookRepo.findById(2);
        Book book = result.orElseThrow();
        log.info("............................");
        log.info(book);
    }

    /*@Test
    public void testUpdate() {
        Optional<Book> result = bookRepo.findById(2);
        Book book = result.orElseThrow();
        book.change("DEMO TITLE", "DEMO AUTHOR", "000000", 2024, "DEMO PUBLISHER");
        bookRepo.save(book);
    }*/

    @Test
    public void testDelete() {
        bookRepo.deleteById(1);
    }

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by r_id descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bNo").descending());
        Page<Book> result = bookRepo.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<Book> bookList = result.getContent();
        bookList.forEach(book -> log.info(book));
    }

    @Test
    public void testSearch1() {

        //2 page order by bNo desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bNo").descending());
        bookRepo.search1(pageable);
    }

    @Test
    public void testUpdateActiveStatus() {
        bookRepo.updateActiveStatus(2);
    }

    @Test
    public void testSearchAll() {
        String[] types = {"t", "a", "i"};
        String keyword = "author";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bNo").descending());
        Page<Book> result = bookRepo.searchAll(types, keyword, pageable, true);

        //total pages
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(book -> log.info(book));
    }
}

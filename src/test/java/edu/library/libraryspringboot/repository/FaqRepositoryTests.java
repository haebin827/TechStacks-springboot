package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Faq;
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
public class FaqRepositoryTests {

    @Autowired
    private FaqRepository fr;

    @Test
    public void testDeleteByfCategory() {
        fr.deleteByfCategory(7);
    }
    /*@Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Faq faq = Faq.builder()
                    .fQuestion("Title " + i)
                    .fAnswer("Answer " + i)
                    .build();

            Faq result = fr.save(faq);
            log.info(result);
        });
    }*/

    @Test
    public void testSelect() {

        int fId = 1;
        Optional<Faq> result = fr.findById(2);
        Faq faq = result.orElseThrow();
        log.info(faq);
    }

    /*@Test
    public void testUpdate() {

        int fId = 1;
        Optional<Faq> result = fr.findById(2);
        Faq faq = result.orElseThrow();
        faq.change("New Title " + fId, "New Answer " + fId);
        fr.save(faq);
    }*/

    @Test
    public void testDelete() {

        fr.deleteById(1);
    }

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by r_id descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fId").descending());
        Page<Faq> result = fr.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<Faq> faqList = result.getContent();
        faqList.forEach(faq -> log.info(faq));
    }

    /*@Test
    public void testSearchAll() {
        String[] types = {"q"};
        String keyword = "new";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fId").descending());
        Page<Faq> result = fr.searchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(faq -> log.info(faq));
    }*/
}

package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.VHistory;
import edu.library.libraryspringboot.domain.VRentalRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@Log4j2
public class VHistoryRepositoryTests {

    @Autowired
    private VHistoryRepository hr;

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by rReqDate descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rRentalDate").descending());

        Page<VHistory> result = hr.findByuId("Haebin", pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<VHistory> historyList = result.getContent();
        historyList.forEach(history -> log.info(history));
    }

    /*@Test
    public void testSearchAll() {
        String[] types = {"t"};
        String keyword = "fou";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rRentalDate").descending());
        Page<VHistory> result = hr.searchAll(types, keyword, pageable, false);

        //total pages
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(book -> log.info(book));
    }*/

}

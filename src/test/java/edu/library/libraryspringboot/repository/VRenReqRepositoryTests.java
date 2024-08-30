package edu.library.libraryspringboot.repository;

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
public class VRenReqRepositoryTests {

    @Autowired
    private VRenReqRepository rr;

    @Test
    public void testRequestCheck() {
        int count = rr.requestCheck("1234");
        log.info("rrv count: " + count);
    }

    /*@Test
    public void testPaging() {
        // 1st page, 10 items per page, order by bNo descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rReqDate").descending());

        // RentalRequestView 리포지토리를 사용하여 페이징 처리
        Page<RentalRequestView> result = rr.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<RentalRequestView> rentalRequestViewList = result.getContent();
        rentalRequestViewList.forEach(rentalRequestView -> log.info(rentalRequestView));
    }*/

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by rReqDate descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("r_req_date").descending());

        Page<VRentalRequest> result = rr.findByUId("1234", pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<VRentalRequest> rentalRequestViewList = result.getContent();
        rentalRequestViewList.forEach(rentalRequestView -> log.info(rentalRequestView));
    }
}

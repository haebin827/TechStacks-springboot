package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Rental;
import edu.library.libraryspringboot.domain.RentalRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class RenReqRepositoryTests {

    @Autowired
    private RenReqRepository rr;

    @Test
    public void testInsert() {
        RentalRequest renReq = RentalRequest.builder()
                .bNo(123)
                .uId("Haebin")
                .build();

        RentalRequest result = rr.save(renReq);
        log.info("result: " + result);
    }

    @Test
    public void testDeleteIncompleteList() {
        rr.deleteIncompleteList("S538108");
    }

}

/*
package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.Rental;
import edu.library.libraryspringboot.dto.RentalDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RenRepositoryTests {

    @Autowired
    private RenRepository renRepo;


    @Test
    public void testInsert() {
        Rental rental = Rental.builder()
                .bNo(123)
                .uId("Haebin")
                .build();

        Rental result = renRepo.save(rental);
        log.info("result: " + result);
    }


    */
/*@Test
    public void testSelect() {
        Long rId = 2L;
        Optional<Rental> result = renRepo.findById(rId);
        Rental rental = result.orElseThrow();
        log.info("............................");
        log.info(rental);
    }*//*


    */
/*@Test
    public void testUpdate() {
        Long rId = 3L;
        Optional<Rental> result = renRepo.findById(rId);
        Rental rental = result.orElseThrow();
        rental.change("test", 150, LocalDateTime.of(2001, 7, 13, 0, 0), LocalDateTime.of(2001, 7, 13, 0, 0), LocalDateTime.of(2001, 7, 13, 0, 0));
        renRepo.save(rental);
    }*//*


    @Test
    public void testDelete() {
        Long rId = 1L;
        renRepo.deleteById(rId);
    }

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by r_id descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rId").descending());
        Page<Rental> result = renRepo.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<Rental> renList = result.getContent();
        renList.forEach(rental -> log.info(rental));
    }

   */
/* @Test
    public void testSearch1() {

        //2 page order by bNo desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rId").descending());
        renRepo.search1(pageable);
    }*//*


    */
/*@Test
    public void testFindRent() {
        String uId = "1234";
        int count = renRepo.findRentCount(uId);
        log.info("............................");
        log.info(count);
    }*//*


*/
/*    @Test
    public void testRentalList() {
        // Execute the query
        List<Object[]> results = renRepo.rentalList("Haebin");

        // Print results
        for (Object[] row : results) {
            String bTitle = (String) row[0];
            String bAuthor = (String) row[1];
            LocalDateTime rRentalDate = (LocalDateTime) row[2];
            LocalDateTime rWhenToReturn = (LocalDateTime) row[3];
            Boolean rIsExtended = (Boolean) row[4];

            System.out.println("Book Title: " + bTitle);
            System.out.println("Book Author: " + bAuthor);
            System.out.println("Rental Date: " + rRentalDate);
            System.out.println("Return Date: " + rWhenToReturn);
            System.out.println("Is Extended: " + rIsExtended);
            System.out.println("---------------------------");
        }

    }*//*


    @Test
    public void testRentalListWithReturnRequest() {
        // Execute the query
        List<Object[]> results = renRepo.rentalList("1");

        // Print results
        for (Object[] row : results) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            LocalDateTime rRentalDate = (LocalDateTime) row[3];
            LocalDateTime rWhenToReturn = (LocalDateTime) row[4];
            Boolean rIsExtended = (Boolean) row[5];
            Boolean rIsReturnReq = (Boolean) row[6];
            Boolean rIsExtReq = (Boolean) row[7];
            LocalDateTime rRtnReqDate = (LocalDateTime) row[8];
            LocalDateTime rExtReqDate = (LocalDateTime) row[9];


            System.out.println("Book No: " + bNo);
            System.out.println("Book Title: " + bTitle);
            System.out.println("Book Author: " + bAuthor);
            System.out.println("Rental Date: " + rRentalDate);
            System.out.println("Return Date: " + rWhenToReturn);
            System.out.println("Is Extended: " + rIsExtended);
            System.out.println("Is ReturnReq: " + rIsReturnReq);
            System.out.println("Rtn Req Date: " + rRtnReqDate);
            System.out.println("Is ExtReq: " + rIsExtReq);
            System.out.println("Ext Req Date: " + rExtReqDate);
            System.out.println("---------------------------");
        }
    }

    @Test
    public void testHistoryList() {
        // Execute the query
        List<Object[]> results = renRepo.historyList("1234");

        // Print results
        for (Object[] row : results) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            LocalDateTime rRentalDate = (LocalDateTime) row[3];
            LocalDateTime rWhenToReturn = (LocalDateTime) row[4];
            LocalDateTime rReturnDate = (LocalDateTime) row[5];
            Boolean rIsExtended = (Boolean) row[6];

            System.out.println("Book No: " + bNo);
            System.out.println("Book Title: " + bTitle);
            System.out.println("Book Author: " + bAuthor);
            System.out.println("Rental Date: " + rRentalDate);
            System.out.println("When To Return: " + rWhenToReturn);
            System.out.println("Return Date: " + rReturnDate);
            System.out.println("Is Extended: " + rIsExtended);
            System.out.println("---------------------------");
        }

    }

    @Test
    public void testUpdateRtnReq() {
        renRepo.updateRtnReq("S538108", 7631);
    }

    @Test
    public void testUpdateExtStatusAndReturnDate() {
        renRepo.updateExtReq("1234", 8464);
    }

}
*/

package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.RentalDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
@Log4j2
public class RenServiceTests {

    @Autowired
    private RenService rs;

    @Test
    public void testRentCount() {
        int count = rs.getRentCount("1234");
        log.info("------------------------");
        log.info(count);
    }

    @Test
    public void testHisCount() {
        int cnt = rs.getHisCnt("1234");
        log.info("his cnt: " + cnt);
    }

    /*@Test
    public void testList() {
        List<Object[]> list = rs.list("S538108");
        for (Object[] row : list) {
            Integer bNo = (Integer) row[0];
            String bTitle = (String) row[1];
            String bAuthor = (String) row[2];
            String rRentalDate = (String) row[3];
            String rWhenToReturn = (String) row[4];
            Boolean rIsExtended = (Boolean) row[5];
            Boolean rIsReturnReq = (Boolean) row[6];
            String rRtnReqDate =  (String) row[7];

            System.out.println("Book No: " + bNo);
            System.out.println("Book Title: " + bTitle);
            System.out.println("Book Author: " + bAuthor);
            System.out.println("Rental Date: " + rRentalDate);
            System.out.println("When To Return: " + rWhenToReturn);
            System.out.println("IsExtended: " + rIsExtended);
            System.out.println("IsReturnReq: " + rIsReturnReq);
            System.out.println("Rental Req Date: " + rRtnReqDate);
            System.out.println("---------------------------");
        }
    }*/

}

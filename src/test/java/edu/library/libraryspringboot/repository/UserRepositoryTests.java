package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepo;


    /*@Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            User user = User.builder()
                    .uId("ID" + i)
                    .uPw("PW" + i)
                    .uName("Name" + i)
                    .uPhone("Phone" + i)
                    .build();

            User result = userRepo.save(user);
            log.info("result: " + result);
        });
    }*/

    @Test
    public void testDeleteUserAfterSixMonths() {
        LocalDateTime date = userRepo.deleteUserAfterSixMonths("4321");
        log.info("DATE: " + date);
    }


    @Test
    public void testSelect() {
        Optional<User> result = userRepo.findById(2);
        User user = result.orElseThrow();
        log.info("............................");
        log.info(user);
    }

    /*@Test
    public void testUpdate() {
        Optional<User> result = userRepo.findById(3);
        User user = result.orElseThrow();
        user.change("fuck", "fuck", "fuck", "fuck", false);
        userRepo.save(user);
    }*/

    @Test
    public void testDelete() {
        userRepo.deleteById(1);
    }

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by r_id descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("uNo").descending());
        Page<User> result = userRepo.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<User> userList = result.getContent();
        userList.forEach(user -> log.info(user));
    }

    @Test
    public void testSearch1() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("uNo").descending());
        userRepo.search1(pageable);
    }

    @Test
    public void testSearchAll() {
        String[] types = {"i", "n"};
        String keyword = "demo";
        Boolean check = true;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("uNo").descending());
        Page<User> result = userRepo.searchAll(types, keyword, pageable, check);

        //total pages
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        result.getContent().forEach(user -> log.info(user));
    }

    @Test
    public void testUpdateUuid() {
        userRepo.updateUuid(5, "!!!!");
    }

    @Test
    public void testFindByuUuid() {
        log.info(userRepo.findByuUuid("f8f15570-46a1-4844-93b8-b3420c23d68f"));
    }

    @Test
    public void testCheckPhone() {
        long count = userRepo.countByuPhone("6605280721");
        log.info("Phone count: " + count);
    }

    @Test
    public void testCheckId() {
        int count = userRepo.checkId("12345");
        log.info("Phone count: " + count);
    }
}

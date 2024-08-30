package edu.library.libraryspringboot.repository;

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
public class NotifRepositoryTests {

    @Autowired
    private NotifRepository notifRepo;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Notification notif = Notification.builder()
                    .nTitle("test" + i)
                    .nContent("testContent " + i)
                    .nIsImp(true)
                    .build();

            Notification result = notifRepo.save(notif);
            log.info("result: " + result);
        });
    }


    @Test
    public void testSelect() {
        Optional<Notification> result = notifRepo.findById(2);
        Notification notif = result.orElseThrow();
        log.info("............................");
        log.info(notif);
    }

    @Test
    public void testUpdate() {
        Optional<Notification> result = notifRepo.findById(2);
        Notification notif = result.orElseThrow();
        notif.change("DEMO TITLE", "DEMO CONTENT", false);
        notifRepo.save(notif);
    }

    @Test
    public void testDelete() {
        notifRepo.deleteById(1);
    }

    @Test
    public void testPaging() {
        // 1st page, 10 items per page, order by r_id descending
        Pageable pageable = PageRequest.of(0, 10, Sort.by("nNo").descending());
        Page<Notification> result = notifRepo.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        List<Notification> notifList = result.getContent();
        notifList.forEach(notif -> log.info(notif));
    }

    @Test
    public void testSearch1() {

        //2 page order by bNo desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("nNo").descending());
        notifRepo.search1(pageable);
    }

    @Test
    public void testUpdateViews() {
        notifRepo.updateViews(1);
    }
}

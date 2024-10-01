package edu.library.libraryspringboot.controller;

import org.apache.commons.collections4.ListUtils;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.service.BookService;
import edu.library.libraryspringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final UserService us;
    private final BookService bs;

    @GetMapping("/home")
    public void homeGET(@ModelAttribute("result") String result,
                        Model model){
        log.info("GET home..................");

        // Fetch top 20 recent books
        List<BookDTO> top20RecentBooks = bs.getTop20RecentBooks();

        // Partition the list into sublists of 5 books each
        List<List<BookDTO>> partitionedBooks = ListUtils.partition(top20RecentBooks, 5);

        log.info("top20RecentBooks: " + top20RecentBooks);
        // Add the partitioned list to the model
        model.addAttribute("partitionedBooks", partitionedBooks);
    }

    @PostMapping("/home")
    public void homePOST(@RequestParam("newPw") String newPw){
        log.info("POST home..................");

        //어드민 비밀번호 변경
        us.setPw(1, newPw);
    }


}

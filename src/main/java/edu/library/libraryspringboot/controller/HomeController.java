package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final UserService us;

    @GetMapping("/home")
    public void homeGET(@ModelAttribute("result") String result){
        log.info("GET home..................");
        log.info("HOME RESULT: " + result);
    }

    @PostMapping("/home")
    public void homePOST(@RequestParam("newPw") String newPw){
        log.info("POST home..................");

        //어드민 비밀번호 변경
        us.setPw(1, newPw);
    }
}

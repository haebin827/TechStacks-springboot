package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final UserService us;

    @GetMapping("/home")

    public void home(@ModelAttribute("result") String result, HttpServletRequest req, Model model){
        log.info("home------------------------");
        log.info("HOME RESULT: " + result);
    }
}

package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.DeletedUserDTO;
import edu.library.libraryspringboot.dto.UserDTO;
import edu.library.libraryspringboot.service.DelUserService;
import edu.library.libraryspringboot.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final UserService us;
    private final DelUserService ds;

    @GetMapping("/login")
    public void loginGET(Model model, HttpServletRequest req) {

        log.info("login GET.......................");

        // Find remember-me cookie
        Cookie rememberCookie = findCookie(req.getCookies(), "remember-me");

        log.info("RememberMe: " + rememberCookie.getValue());

        if (rememberCookie != null && !rememberCookie.getValue().isEmpty()) {

            // If remember-me exists
            String uUuid = rememberCookie.getValue();
            log.info("remember-me cookie found: " + uUuid);

            try {
                // Retrieve user information by UUID and add it to the model
                UserDTO userDTO = us.getByUUID(uUuid);

                if (userDTO != null) {
                    model.addAttribute("uId", userDTO.getUId());
                }
            } catch (Exception e) {
                log.error("Error retrieving member by UUID: ", e);
            }
        }
    }

    @PostMapping("/login")
    public String loginPOST(HttpServletRequest req, HttpServletResponse resp, RedirectAttributes redirectAttributes) {

        log.info("login POST.......................");

        String uId = req.getParameter("uId");
        String uPw = req.getParameter("uPw");
        String rememberBox = req.getParameter("rememberBox"); // Use 'rememberBox'

        boolean rememberMe = rememberBox != null && rememberBox.equals("on");

        try {
            UserDTO userDTO = us.verify(uId, uPw);
            HttpSession session = req.getSession();

            if(userDTO.getUNo() == 1) {
                session.setAttribute("adminLogin", userDTO);

                // 만약 어드민의 첫 로그인이라면, 비밀번호 변경유도
                if(userDTO.getUPw().equals("0000")) {
                    redirectAttributes.addFlashAttribute("result", "Admin first time login");
                }
            }
            else {
                session.setAttribute("userLogin", userDTO);

                // Handle rememberMe (assign UUID)
                if (rememberMe) {
                    String uuid = UUID.randomUUID().toString();
                    log.info("Random uuid: " + uuid);

                    us.setUuid(userDTO.getUNo(), uuid);
                    log.info("MemberDTO: " + userDTO);

                    Cookie rememberCookie = new Cookie("remember-me", uuid);
                    rememberCookie.setPath("/");
                    rememberCookie.setMaxAge(60*60*24*7); // Valid for one week

                    resp.addCookie(rememberCookie);
                } else {
                    Cookie rememberCookie = new Cookie("remember-me", "");
                    rememberCookie.setPath("/");
                    rememberCookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
                    resp.addCookie(rememberCookie);
                }

                // 6개월이 지난 회원 자동삭제 날짜를 확인하고, HOME에서 남은 날짜 알려주기
                int dateLeft = us.checkAutomaticDeletionDate(uId);

                if (dateLeft >= 0 && dateLeft <= 7) {
                    redirectAttributes.addFlashAttribute("dateLeft", String.valueOf(dateLeft));
                    redirectAttributes.addFlashAttribute("uRegDate", userDTO.getURegDate());
                }
                log.info("Check dateLeft value: " + dateLeft);

                // 만약 6개월이 지났다면 회원 자동삭제
                if(dateLeft == -2) {

                    log.info("DELETE USER");
                    // DeletedUser에 회원정보 추가
                    DeletedUserDTO delUserDTO = DeletedUserDTO.builder()
                            .uId(uId)
                            .dReason("AUTOMATICALLY DELETED")
                            .build();

                    ds.register(delUserDTO);

                    us.remove(userDTO.getUNo());

                    // 세션 삭제
                    session.removeAttribute("userLogin");
                    session.invalidate();

                    return "redirect:/user/login";
                }

                if(userDTO.getUIsLevelUp()) {
                    redirectAttributes.addFlashAttribute("result", "Level up");
                    redirectAttributes.addFlashAttribute("level", userDTO.getULevel());
                    us.setLevelUp(userDTO.getUId());
                }

            }

            session.setAttribute("uName", userDTO.getUName());
            session.setAttribute("uId", userDTO.getUId());
            session.setAttribute("uNo", userDTO.getUNo());

            log.info("-------------------------------------");
            log.info("AdminLogin: " + session.getAttribute("adminLogin"));
            log.info("UserLogin: " + session.getAttribute("userLogin"));

            return "redirect:/home";

        } catch (Exception e) {
            log.info("Login fail ......................: " + e);
            return "redirect:/user/login?result=error";
        }
    }


@PostMapping("/logout")
    public String logoutPOST(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("POST member logout......................");

        HttpSession session = req.getSession();

        session.removeAttribute("adminLogin");
        session.removeAttribute("userLogin");

        session.invalidate();
        log.info("removed------------------------");

        return "redirect:/home";
    }

    @GetMapping("/register")
    protected void registerGET(Model model) {

        log.info("GET member register ......................");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid UserDTO userDTO,
                               BindingResult bindingResult,
                               HttpServletRequest req,
                               HttpServletResponse resp,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("uPw") String uPw,
                               @RequestParam("confirm") String confirmPw,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) throws IOException {

        log.info("POST member register...................");

        if (bindingResult.hasErrors()) {
            log.info("Has errors......................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("firstName", firstName);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            return "redirect:/user/register";
        }

        //phone validation
        if(us.verifyPhone(userDTO.getUPhone()) > 0) {
            log.info("PHONE ERROR");
            redirectAttributes.addFlashAttribute("ERROR", "phoneError");
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("firstName", firstName);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            return "redirect:/user/register";
        }

        //student id validation
        if(us.verifyId(userDTO.getUId()) > 0) {
            log.info("ID ERROR");
            redirectAttributes.addFlashAttribute("ERROR", "idError");
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("firstName", firstName);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            return "redirect:/user/register";
        }

        us.register(userDTO);
        redirectAttributes.addFlashAttribute("uId", userDTO.getUId());
        return "redirect:/user/registerConfirmed";
    }

    @GetMapping("/registerConfirmed")
    protected void registerConfirmedGET(Model model) {

    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0) {
            for(Cookie ck: cookies) {
                if(ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }
        return targetCookie;
    }
}

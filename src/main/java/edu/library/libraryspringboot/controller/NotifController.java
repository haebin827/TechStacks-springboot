package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.NotificationDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.service.NotifService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notif")
@Log4j2
@RequiredArgsConstructor
public class NotifController {

    private final NotifService ns;

    @GetMapping("/list")
    public void listGET(PageRequestDTO pgReqDTO, Model model) {

        PageResponseDTO<NotificationDTO> respDTO = ns.list(pgReqDTO);
        log.info("list GET...............");

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @GetMapping("/register")
    public void registerGET(Model model) {

    }

    @PostMapping("/register")
    public String registerPOST(@Valid NotificationDTO notifDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        log.info("notif register POST...............");
        if(bindingResult.hasErrors()) {
            log.info("has errors...............");
            log.info(notifDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/notif/register";
        }

        log.info(notifDTO);
        ns.register(notifDTO);
        redirectAttributes.addFlashAttribute("result", "Registered");

        return "redirect:/notif/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(int nNo, PageRequestDTO pgReqDTO, Model model, HttpServletRequest req) {

        log.info("notif read GET...............");
        NotificationDTO notifDTO = ns.readOne(nNo);
        log.info("notifDTO: " + notifDTO);

        // 클릭할 때마다 조회수 올리기
        HttpSession session = req.getSession();

        if (session.getAttribute("adminLogin") == null) {
            ns.setViews(nNo);
        }

        model.addAttribute("dto", notifDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageReqDTO,
                         @Valid NotificationDTO notifDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("Notif Modify register................");
        log.info("Modified notifDTO: " + notifDTO);

        if(bindingResult.hasErrors()) {
            log.info("Has errors..................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("nNo", notifDTO.getNNo());
            return "redirect:/notif/modify?"+pageReqDTO.getLink();
        }

        ns.modify(notifDTO);
        redirectAttributes.addFlashAttribute("result", "Modified");

        return "redirect:/notif/list";
    }

    @PostMapping("/remove")
    public String remove(int nNo, RedirectAttributes redirectAttributes) {
        log.info("Notif POST Remove .............. : ");
        NotificationDTO notifDTO = ns.readOne(nNo);
        ns.remove(nNo);

        log.info("removed NotifDTO: " + notifDTO);

        redirectAttributes.addFlashAttribute("result", "Removed");

        return "redirect:/notif/list";
    }
}

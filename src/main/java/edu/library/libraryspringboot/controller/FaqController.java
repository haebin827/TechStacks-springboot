package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.domain.FaqCategory;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/faq")
@Log4j2
@RequiredArgsConstructor
public class FaqController {

    private final FaqService fs;

    @GetMapping("/list")
    public void listGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("Faq List GET................");

        PageResponseDTO<FaqDTO> respDTO = fs.list(pgReqDTO);
        List<FaqCategoryDTO> catList = fs.catList();
        log.info(respDTO);

        model.addAttribute("catList", catList);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @GetMapping("/register")
    public void registerGET(Model model) {
        log.info("Faq Register GET...............");

        List<FaqCategoryDTO> catList = fs.catList();
        model.addAttribute("catList", catList);

    }

    @PostMapping("/register")
    public String registerPOST(@Valid FaqDTO faqDTO,
                               @RequestParam("fCategoryNo") String fCategory,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("FAQ register POST...............");
        if(bindingResult.hasErrors()) {
            log.info("has errors...............");
            log.info(faqDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/faq/register";
        }

        log.info("faqDTO: " + faqDTO);

        faqDTO.setFCategory(fCategory);
        fs.register(faqDTO);
        redirectAttributes.addFlashAttribute("result", "Registered");

        return "redirect:/faq/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(int fId, PageRequestDTO pgReqDTO, Model model) {

        log.info("FAQ read GET...............");
        FaqDTO faqDTO = fs.readOne(fId);
        log.info("faqDTO: " + faqDTO);

        List<FaqCategoryDTO> catList = fs.catList();

        model.addAttribute("catList", catList);
        model.addAttribute("dto", faqDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pgReqDTO,
                         @Valid FaqDTO faqDTO,
                         @RequestParam("fCategory") String fCategory,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("FAQ Modify register................");
        log.info("Modified FaqDTO: " + faqDTO);

        if(bindingResult.hasErrors()) {
            log.info("Has errors..................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("fId", faqDTO.getFId());
            return "redirect:/faq/modify?"+pgReqDTO.getLink();
        }

        faqDTO.setFCategory(fCategory);
        log.info("fCategory: " + faqDTO);
        fs.modify(faqDTO);
        redirectAttributes.addFlashAttribute("result", "Modified");

        return "redirect:/faq/list";
    }

    @PostMapping("/remove")
    public String remove(int fId, RedirectAttributes redirectAttributes) {
        log.info("FAQ POST Remove .............. : ");
        FaqDTO faqDTO = fs.readOne(fId);
        fs.remove(fId);

        log.info("removed FaqDTO: " + faqDTO);

        redirectAttributes.addFlashAttribute("result", "Removed");

        return "redirect:/faq/list";
    }
}

package edu.library.libraryspringboot.controller;

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

        log.info("faq GET list................");

        if (pgReqDTO.getGroup() == null) {
            pgReqDTO.setGroup(-1);
        }

        PageResponseDTO<FaqDTO> respDTO = fs.list(pgReqDTO);
        List<FaqCategoryDTO> catList = fs.catList();
        log.info(respDTO);

        log.info("WORD: " + pgReqDTO.getWord());

        model.addAttribute("catList", catList);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    /*@PostMapping("/list")
    public String listPOST(PageRequestDTO pgReqDTO, Model model) {

        log.info("Faq List POST................");

        PageResponseDTO<FaqDTO> respDTO = fs.list(pgReqDTO);
        List<FaqCategoryDTO> catList = fs.catList();
        log.info(respDTO);

        log.info("group:" + pgReqDTO.getGroup());
        log.info("word:" + pgReqDTO.getWord());
        log.info("link:" + pgReqDTO.getLink());
        model.addAttribute("catList", catList);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);

        return "redirect:/faq/list?" + pgReqDTO.getLink();  // Redirect to GET with parameters
    }*/

    @GetMapping("/register")
    public void registerGET(Model model) {

        log.info("faq GET register...............");

        List<FaqCategoryDTO> catList = fs.catList();
        model.addAttribute("catList", catList);
    }

    @PostMapping("/register")
    public String registerPOST(@Valid FaqDTO faqDTO,
                                @RequestParam("fCategoryNo") int fCategory,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        log.info("faq POST register...............");

        if (bindingResult.hasErrors()) {
            log.info("has errors...............");
            log.info(faqDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/faq/register";
        }

        faqDTO.setFCategory(fCategory);
        fs.register(faqDTO);

        redirectAttributes.addFlashAttribute("result", "Registered");
        return "redirect:/faq/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(int fId, PageRequestDTO pgReqDTO, Model model) {

        log.info("faq GET read...............");

        FaqDTO faqDTO = fs.readOne(fId);
        log.info("faqDTO: " + faqDTO);

        List<FaqCategoryDTO> catList = fs.catList();

        // 해당 카테고리 이름 찾기
        String categoryName = catList.stream()
                .filter(cat -> cat.getFNo() == faqDTO.getFCategory())
                .map(FaqCategoryDTO::getFName)
                .findFirst()
                .orElse("Unknown Category"); // 카테고리를 찾지 못한 경우 "Unknown Category" 사용

        model.addAttribute("categoryName", categoryName);  // 찾은 카테고리 이름을 model에 추가
        model.addAttribute("catList", catList);
        model.addAttribute("dto", faqDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pgReqDTO,
                         @Valid FaqDTO faqDTO,
                         @RequestParam("fCategory") Integer fCategory,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("faq POST modify................");

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
    public String removePOST(int fId, RedirectAttributes redirectAttributes) {

        log.info("faq POST remove..............");

        FaqDTO faqDTO = fs.readOne(fId);
        fs.remove(fId);

        redirectAttributes.addFlashAttribute("result", "Removed");
        return "redirect:/faq/list";
    }
}

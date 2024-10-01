package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bs;
    private final WishlistService ws;
    private final DelBookService ds;
    private final CatService cs;
    private final RenService rr;
    private final RenReqService rrs;

    @GetMapping("/list")
    public void listGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("book GET list..............");

        if (pgReqDTO.getCat() == null) {
            pgReqDTO.setCat("0");
        }

        PageResponseDTO<BookDTO> respDTO = bs.list(pgReqDTO);
        List<CategoryDTO> catDTO = cs.catList();

        model.addAttribute("catDTO", catDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
/*
    HttpSession session = req.getSession();

        if(session.isNew()) {
            log.info("JSESSIONID: User with active session");
            return "redirect:/user/login";
        }

        if(session.getAttribute("userLogin") == null) {
            log.info("JSESSIONID: User without login info");
            return "redirect:/user/login";
        }*/

    }

    @GetMapping("/register")
    public void registerGET(Model model) {

        log.info("book GET register..............");

        List<CategoryDTO> catDTO = cs.catList();
        model.addAttribute("catDTO", catDTO);
    }

    @PostMapping("/register")
    public String registerPOST(@Valid BookDTO bookDTO,
                               @RequestParam("bCategory") String bCategory,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("book POST register..............");

        if(bindingResult.hasErrors()) {
            log.info("Has errors..................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/book/register";
        }
        log.info(bookDTO);

        bookDTO.setBCategory(bCategory);
        bs.register(bookDTO);

        redirectAttributes.addFlashAttribute("result", "Added");
        redirectAttributes.addFlashAttribute("bTitle", bookDTO.getBTitle());

        return "redirect:/book/list";
    }

    @GetMapping({"/read", "/modify"})
    public void readGET(int bNo, PageRequestDTO pgReqDTO, Model model, HttpServletRequest req) {

        log.info("book GET read, modify..............");

        BookDTO bookDTO = bs.readOne(bNo);
        log.info("bookDTO: " + bookDTO);
        log.info("GetLink: " + pgReqDTO.getLink());

        //rr.getRentCount()

        //wishlist에 값이 존재하면 채워진 하트로 표현되게 함
        HttpSession session = req.getSession();
        if(session.getAttribute("userLogin") != null) {
            int cnt = ws.getWListCnt((String) session.getAttribute("uId"), bNo);
            log.info("CNT: " + cnt);
            if (cnt > 0) {
                model.addAttribute("wishlist", true);
            }
        }

        List<CategoryDTO> catDTO = cs.catList();

        model.addAttribute("catDTO", catDTO);
        model.addAttribute("dto", bookDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pgReqDTO,
                         @Valid BookDTO bookDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("book POST modify................ : " + bookDTO);

        log.info("Modified bookDTO: " + bookDTO);

        if(bindingResult.hasErrors()) {
            log.info("Has errors..................");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bNo", bookDTO.getBNo());
            return "redirect:/book/modify?"+pgReqDTO.getLink();
        }

        bs.modify(bookDTO);
        redirectAttributes.addFlashAttribute("bTitle", bookDTO.getBTitle());
        redirectAttributes.addFlashAttribute("result", "Modified");

        return "redirect:/book/list";
    }

    @PostMapping("/remove")
    public String removePOST(int bNo,
                         @RequestParam("deletionReason") String deletionReason,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pgReqDTO) {

        log.info("book POST remove ..............");

        BookDTO bookDTO = bs.readOne(bNo);
        bs.modifyActiveStatus(bNo);

        DeletedBookDTO delBookDTO = DeletedBookDTO.builder()
                        .bTitle(bookDTO.getBTitle())
                        .dReason(deletionReason)
                        .build();
        ds.register(delBookDTO);

        redirectAttributes.addFlashAttribute("result", "Removed");
        redirectAttributes.addFlashAttribute("bTitle", bookDTO.getBTitle());

        return "redirect:/book/list?" + pgReqDTO.getLink();
    }

    @PostMapping("/rent")
    public String rentPOST(RedirectAttributes redirectAttributes,
                           BookDTO bookDTO,
                           @RequestParam("bNo") int bNo,
                           @RequestParam("bTitle") String bTitle,
                         HttpServletRequest req,
                         PageRequestDTO pgReqDTO) {

        log.info("book POST rent..............");

        log.info("BOOK BUNDLE:" + bookDTO);
        HttpSession session = req.getSession();
        int rentCnt = rr.getRentCount((String) session.getAttribute("uId"));
        int reqCnt = rrs.isRequested((String) session.getAttribute("uId"), bNo);
        log.info("RENT COUNT: " + rentCnt);

        if(rentCnt >= 3) {
            redirectAttributes.addFlashAttribute("result", "limit");
            return "redirect:/book/read?bNo=" + bNo + "&" + pgReqDTO.getLink();
        }
        if(reqCnt > 0) {
            redirectAttributes.addFlashAttribute("result", "requested");
            return "redirect:/book/read?bNo=" + bNo + "&" + pgReqDTO.getLink();
        }

        // Add new record to RentalRequest
        RentalRequestDTO renReqDTO = RentalRequestDTO.builder()
                .bNo(bNo)
                .uId((String) session.getAttribute("uId"))
                .build();
        rrs.register(renReqDTO);

        redirectAttributes.addFlashAttribute("result", "Rented");
        redirectAttributes.addFlashAttribute("bTitle", bTitle);
        return "redirect:/book/list";
    }

    @PostMapping("/wishlist")
    public String wishlistPOST(PageRequestDTO pgReqDTO,
                               BookDTO bookDTO,
                               HttpServletRequest req,
                               Model model) {

        log.info("book POST wishlist..............");

        log.info("BOOK DTO WISH: " + bookDTO.toString());
        HttpSession session = req.getSession();

        // Add new record to wishlist
        WishlistDTO wListDTO = WishlistDTO.builder()
                .uId((String) session.getAttribute("uId"))
                .bNo(bookDTO.getBNo())
                .build();
        ws.register(wListDTO);

        log.info("PAGE REQUEST: " + pgReqDTO.getLink());
        model.addAttribute("pgReqDTO", pgReqDTO);
        return "redirect:/book/read?bNo=" + bookDTO.getBNo() + "&" + pgReqDTO.getLink();
    }

    @PostMapping("/cancelWishlist")
    public String cancelWishlistPOST(BookDTO bookDTO,
                                    HttpServletRequest req,
                                    PageRequestDTO pgReqDTO, Model model) {

        log.info("book POST cancelWishlist..............");

        HttpSession session = req.getSession();
        ws.remove((String) session.getAttribute("uId"), bookDTO.getBNo());

        model.addAttribute("pgReqDTO", pgReqDTO);
        return "redirect:/book/read?bNo=" + bookDTO.getBNo() + "&" + pgReqDTO.getLink();
    }
}

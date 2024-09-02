package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/myPage")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final RenService rs;
    private final DelUserService ds;
    private final UserService us;
    private final WishlistService ws;
    private final RenReqService rrs;
    private final RtnReqService rts;
    private final ExtReqService es;

    @GetMapping("/home")
    public void homeGET(HttpServletRequest req, Model model) {

        HttpSession session = req.getSession();

        UserDTO userDTO = us.readOne((int)session.getAttribute("uNo"));
        int rentCnt = rs.getRentCount((String)session.getAttribute("uId"));
        int rentHstryCnt = rs.getRentHstryCount((String)session.getAttribute("uId"));
        int reqCnt = rrs.getReqCount((String)session.getAttribute("uId"));

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("rentCnt", rentCnt);
        model.addAttribute("rentHstryCnt", rentHstryCnt);
        model.addAttribute("reqCnt", reqCnt);

    }

    @GetMapping("/booklist")
    public void booklistGET(HttpServletRequest req,
                            Model model) {

        HttpSession session = req.getSession();

        //(아직 개발안함) rental 테이블에서 rId가 인덱스임. isExtended가 1인 경우, extend 그만하게하기
        List<Object[]> dtoList = rs.list((String)session.getAttribute("uId"));
        model.addAttribute("dto", dtoList);
    }

    @GetMapping("/account/view")
    public void viewGET(HttpServletRequest req,
                            Model model) {

        HttpSession session = req.getSession();
        UserDTO userDTO = us.readOne((int)session.getAttribute("uNo"));
        model.addAttribute("dto", userDTO);
    }

    @GetMapping("/account/edit")
    public void editGET(HttpServletRequest req,
                        Model model) {

        HttpSession session = req.getSession();
        UserDTO userDTO = us.readOne((int)session.getAttribute("uNo"));
        model.addAttribute("dto", userDTO);
    }

    @PostMapping("/account/edit")
    public String editPost(HttpServletRequest req,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("uName") String uName,
                           @RequestParam("uPhone") String uPhone) {

        HttpSession session = req.getSession();
        UserDTO userDTO = us.readOne((int)session.getAttribute("uNo"));
        //phone validation 체크
        if(us.verifyPhone(uPhone) > 0 && !userDTO.getUPhone().equals(uPhone)) {
            redirectAttributes.addFlashAttribute("result", "duplicate");
            return "redirect:/myPage/account/edit";
        }

        // account update
        us.setAcct((int)session.getAttribute("uNo"), uName, uPhone);

        return "redirect:/myPage/account/view";
    }

    @PostMapping("/changePw")
    public String changePw(HttpServletRequest req,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("newPw") String newPw) {

        HttpSession session = req.getSession();
        us.setPw((int)session.getAttribute("uNo"), newPw);

        redirectAttributes.addFlashAttribute("result", "password changed");
        return "redirect:/myPage/home";
    }

    //유저 계정삭제
    @PostMapping("/remove")
    public String removeGET(HttpServletRequest req, RedirectAttributes redirectAttributes) {

        HttpSession session = req.getSession();

        // 해당 uId와 관련된, 완료되지 않은 Rental request 모두삭제
        rrs.removeIncompleteList((String)session.getAttribute("uId"));

        // deletedUser 레코드 삽입
        DeletedUserDTO delUserDTO = DeletedUserDTO.builder()
                .uId((String)session.getAttribute("uId"))
                .dReason("BY USER")
                .build();
        ds.register(delUserDTO);

        DeletedUserDTO delUser = ds.readOneByUId((String)session.getAttribute("uId"));

        // LocalDateTime 형식을 yyyy-MM-dd로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDelDate = delUser.getDDelDate().format(formatter);

        // 유저 삭제
        us.remove((int)session.getAttribute("uNo"));

        redirectAttributes.addFlashAttribute("result", "Deleted");
        redirectAttributes.addFlashAttribute("delDate", formattedDelDate);

        // 세션 삭제
        session.removeAttribute("userLogin");
        session.invalidate();

        return "redirect:/home";
    }

    @PostMapping("/return")
    public String returnPOST(HttpServletRequest req,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();

        // ReturnRequest
        ReturnRequestDTO rtnDTO = ReturnRequestDTO.builder()
                        .bNo(bNo)
                        .uId((String)session.getAttribute("uId"))
                        .build();
        rts.register(rtnDTO);

        // rental 레코드의 isReturnReq 을 1로 변경
        rs.setRtnReq((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/booklist";
    }

    @PostMapping("/cancelReturn")
    public String cancelReturn(HttpServletRequest req,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();

        //해당하는 return req 삭제
        rts.remove((String)session.getAttribute("uId"), bNo);

        //rental 레코드의 isReturnReq 을 0으로 변경
        rs.setRtnReq((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/booklist";
    }

    @PostMapping("/extend")
    public String extendPOST(HttpServletRequest req,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();

        // ExtendRequest
        ExtensionRequestDTO extDTO = ExtensionRequestDTO.builder()
                .bNo(bNo)
                .uId((String)session.getAttribute("uId"))
                .build();
        es.register(extDTO);

        // rental 레코드의 isReturnReq 을 1로 변경
        rs.setExtReq((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/booklist";
    }

    @PostMapping("/cancelExtend")
    public String cancelExtend(HttpServletRequest req,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();

        //해당하는 extension req 삭제
        es.remove((String)session.getAttribute("uId"), bNo);

        //extension 레코드의 isExtReq 을 0으로 변경
        rs.setExtReq((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/booklist";
    }

    /*@GetMapping("/reqlist")
    public void reqlistGET(HttpServletRequest req, PageRequestDTO pgReqDTO, Model model) {

        HttpSession session = req.getSession();

        List<Object[]> dtoList = rrs.list((String)session.getAttribute("uId"));
        model.addAttribute("dto", dtoList);
    }*/

    @GetMapping("/reqlist")
    public void reqlistGET(HttpServletRequest req, PageRequestDTO pgReqDTO, Model model) {

        HttpSession session = req.getSession();

        PageResponseDTO<VRentalRequestDTO> respDTO = rrs.list(pgReqDTO, (String)session.getAttribute("uId"));
        log.info(respDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/cancelRental")
    public String cancelRentalPOST(PageRequestDTO pgReqDTO,
                                   HttpServletRequest req,
                               @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();

        //해당하는 rental req 삭제
        rrs.removeRenReq((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/reqlist";
    }

    /*@GetMapping("/history")
    public void historyGET(HttpServletRequest req, PageRequestDTO pgReqDTO, Model model) {

        HttpSession session = req.getSession();

        List<Object[]> dtoList = rs.historyList((String)session.getAttribute("uId"));
        model.addAttribute("dto", dtoList);
    }*/

    @GetMapping("/history")
    public void historyGET(HttpServletRequest req,
                           PageRequestDTO pgReqDTO,
                           Model model) {

        HttpSession session = req.getSession();

        PageResponseDTO<VHistoryDTO> respDTO = rs.historyList(pgReqDTO, (String)session.getAttribute("uId"));
        log.info(respDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @GetMapping("/wishlist")
    public void wishlistGET(HttpServletRequest req,
                            PageRequestDTO pgReqDTO,
                            Model model) {

        HttpSession session = req.getSession();
        PageResponseDTO<VWishlistDTO> respDTO = ws.listAll(pgReqDTO, (String)session.getAttribute("uId"));

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/cancelWishlist")
    public String cancelWishlistPOST(HttpServletRequest req,
                            @RequestParam("bNo") int bNo) {

        HttpSession session = req.getSession();
        ws.remove((String)session.getAttribute("uId"), bNo);

        return "redirect:/myPage/wishlist";
    }
}

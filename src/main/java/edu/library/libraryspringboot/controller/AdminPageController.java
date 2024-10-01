package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/adminPage")
@Log4j2
@RequiredArgsConstructor
public class AdminPageController {

    private final UserService us;
    private final BookService bs;
    private final RenService rs;
    private final FaqService fs;
    private final BlklistService bls;
    private final DelBookService ds;
    private final RenReqService rrs;
    private final RtnReqService rts;
    private final ExtReqService es;


/*
    |--------------------------------------------------------------------------
    | Home
    |--------------------------------------------------------------------------
    |
    | Admin Dashboard
    |
    |
    */


    @GetMapping("/home")
    public void homeGET(Model model) {

        log.info("adminPage GET home..............");

        model.addAttribute("renReqCnt", rrs.getAllReqCount());
        model.addAttribute("rtnReqCnt", rts.getAllReqCount());
        model.addAttribute("extReqCnt", es.getAllReqCount());
        model.addAttribute("hisCnt", rs.getAllCount());
    }


/*
    |--------------------------------------------------------------------------
    | Loan Requests (Rental, Return, Extension)
    |--------------------------------------------------------------------------
    |
    | Review/Search/Process user-submitted book requests
    |
    |   renReq: Rental Request
    |   extReq: Extension Request
    |   rtnReq: Return Request
    |
    */



/*@GetMapping("/renReq")
    public void renReqGET(Model model) {

        List<Object[]> dtoList = rrs.ListAll();
        model.addAttribute("dto", dtoList);
    }*/


    @GetMapping("/renReq")
    public void renReqGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("adminPage GET renReq..............");

        PageResponseDTO<VAdminRentalRequestDTO> respDTO = rrs.listAll(pgReqDTO);
        log.info(respDTO);

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/renReq")
    public String renReqPOST(@RequestParam("uId") String uId,
                             @RequestParam("bNo") int bNo) {

        log.info("adminPage POST renReq..............");

        // rental 레코드 추가
        RentalDTO renDTO = RentalDTO.builder()
                .uId(uId)
                .bNo(bNo)
                .build();
        rs.register(renDTO);

        //renreq 의 renreq 값을 0으로
        rrs.setRenReq(uId, bNo);

        //book의 isRental값을 1로
        bs.setRenStatus(bNo);

        return "redirect:/adminPage/renReq";
    }


/*@GetMapping("/extReq")
    public void extReqGET(Model model) {

        List<Object[]> dtoList = es.ListAll();
        model.addAttribute("dto", dtoList);
    }*/


    @GetMapping("/extReq")
    public void extReqGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("adminPage GET extReq..............");

        PageResponseDTO<VAdminExtensionRequestDTO> respDTO = es.listAll(pgReqDTO);
        log.info(respDTO);

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/extReq")
    public String extReqPOST(@RequestParam("uId") String uId,
                             @RequestParam("bNo") int bNo) {

        log.info("adminPage POST extReq..............");

        //extReq 의 extReq 값을 0으로
        es.setExtReq(uId, bNo);

        //rental의 isExtReq 값을 0 으로, isExtended를 1로, when to return을 15일 뒤로
        rs.setExtStatus(uId, bNo);

        return "redirect:/adminPage/extReq";
    }


/*@GetMapping("/rtnReq")
    public void rtnReqGET(Model model) {

        List<Object[]> dtoList = rts.ListAll();
        model.addAttribute("dto", dtoList);
    }*/


    @GetMapping("/rtnReq")
    public void rtnReqGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("adminPage GET rtnReq..............");

        PageResponseDTO<VAdminReturnRequestDTO> respDTO = rts.listAll(pgReqDTO);
        log.info(respDTO);

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }


/*@PostMapping("/rtnReq")
    public String rtnReqPOST(@RequestParam("uId") String uId,
                             @RequestParam("bNo") int bNo,
                             @RequestParam("bCondition") String status,
                             @RequestParam("rReqDate") String rtnDate,
                             @RequestParam("rWhenToReturn") String deadline) {

        log.info("Status: " + status);

        // Convert strings to LocalDate
        LocalDate returnDate = LocalDate.parse(rtnDate);
        LocalDate returnDeadline = LocalDate.parse(deadline);

        // Compare the dates
        if (returnDate.isAfter(returnDeadline)) {
            us.setLateRtn(uId);

            // Blacklist 테이블에 레코드 추가
            BlacklistDTO blklistDTO = BlacklistDTO.builder()
                    .uId(uId)
                    .blReason("LATE RETURN")
                    .build();
            bls.register(blklistDTO);
        }

        // Book에 isRental = 0으로 바꾸고 컨디션 업데이트
        bs.setRtnStatus(bNo, status);
        // Rental에 returnDate=now, isReturnReq = false, isReturned = true로 업데이트
        rs.setRtnStatus(uId, bNo);
        // RentalRequest에 rtnreq = 0으로 업데이트
        rts.setRtnReq(uId, bNo);

        return "redirect:/adminPage/rtnReq";
    }*/


    @PostMapping("/rtnReq")
    public String rtnReqPOST(VAdminReturnRequestDTO rtnReqDTO) {

        log.info("adminPage POST rtnReq..............");

        // Convert strings to LocalDate
        LocalDate returnDate = LocalDate.from(rtnReqDTO.getRReqDate());
        LocalDate returnDeadline = LocalDate.from(rtnReqDTO.getRWhenToReturn());

        log.info("RETURN DATE: " + returnDate);
        log.info("DEADLINE DATE: " + returnDeadline);

        // Compare the dates
        if (returnDate.isAfter(returnDeadline)) {
            us.setLateRtn(rtnReqDTO.getUId());

            // Blacklist 테이블에 레코드 추가
            BlacklistDTO blklistDTO = BlacklistDTO.builder()
                    .uId(rtnReqDTO.getUId())
                    .blReason("LATE RETURN (Title '" + rtnReqDTO.getBTitle() + "')")
                    .build();
            bls.register(blklistDTO);
        } else {
            // 블랙리스트 아니라면 500포인트 추가
            us.setPoint(500, rtnReqDTO.getUId());
        }

        // Book에 isRental = 0으로 바꾸고 컨디션 업데이트
        bs.setRtnStatus(rtnReqDTO.getBNo(), rtnReqDTO.getBCondition());
        // Rental에 returnDate=now, isReturnReq = false, isReturned = true로 업데이트
        rs.setRtnStatus(rtnReqDTO.getUId(), rtnReqDTO.getBNo());
        // RentalRequest에 rtnreq = 0으로 업데이트
        rts.setRtnReq(rtnReqDTO.getUId(), rtnReqDTO.getBNo());
        
        //레벨체크(만약 HISTORY LIST에 있는 책 권수가 특정한 수보다 많다면 레벨 업데이트)
        // BRONZE: 0
        // SILVER: 10
        // GOLD: 30
        // PLATINUM: 60
        // DIAMOND: 100
        // RUBY: 150
        // EMERALD: 220
        // OBSIDIAN: 300
        int cnt = rs.getHisCnt(rtnReqDTO.getUId());

        log.info("cnt: " + cnt);
        String[] levels = {"OBSIDIAN", "EMERALD", "RUBY", "DIAMOND", "PLATINUM", "GOLD", "SILVER"};
        int[] bookCnts = {300, 220, 150, 100, 60, 30, 10};

        for (int i = 0; i < bookCnts.length; i++) {
            log.info("level: " + levels[i] + ", bookCnt: " + bookCnts[i]);
            if (cnt >= bookCnts[i]) {
                if (rtnReqDTO.getULevel().equals(levels[i])) {
                    log.info("SAME");
                    continue;
                }
                log.info("UPDATED");
                us.setLevel(levels[i], rtnReqDTO.getUId());
                us.setLevelUp(rtnReqDTO.getUId());
                break;
            }
        }

        return "redirect:/adminPage/rtnReq";
    }


/*
    |--------------------------------------------------------------------------
    | History list
    |--------------------------------------------------------------------------
    |
    | Review/Search current users' book rental status and history
    |
    |
    */


    @GetMapping("/history")
    public void historyGET(PageRequestDTO pgReqDTO, Model model) {

        log.info("adminPage GET history..............");

        PageResponseDTO<VAdminHistoryDTO> respDTO = rs.adminHisList(pgReqDTO);
        log.info(respDTO);
        log.info(pgReqDTO);

        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }


/*
    |--------------------------------------------------------------------------
    | FAQ Category Management
    |--------------------------------------------------------------------------
    |
    | CRUD functionality for FAQ categories.
    | Functionality to move all posts from one category to another.
    |
    |
    */


    @GetMapping("/faq/categories")
    public void faqCatListGET(Model model) {

        log.info("adminPage GET /faq/categories..............");

        List<FaqCategoryDTO> catList = fs.catList();
        model.addAttribute("catList", catList);
    }

    @PostMapping("/faq/register")
    public String faqCatRegisterPOST(@RequestParam("newCatName") String newCatName) {

        log.info("adminPage POST /faq/register...............");

        FaqCategoryDTO faqCatDTO = FaqCategoryDTO.builder()
                .fName(newCatName)
                .build();

        fs.catRegister(faqCatDTO);

        return "redirect:/adminPage/faq/categories";
    }

    @PostMapping("/faq/modify")
    public String faqCatModifyPOST(FaqCategoryDTO faqCatDTO,
                                   @RequestParam("newCatName") String newCatName) {

        log.info("adminPage POST /faq/modify..............");

        fs.setCatName(newCatName, faqCatDTO.getFNo());
        return "redirect:/adminPage/faq/categories";
    }

    @PostMapping("/faq/remove")
    public String faqCatRemovePOST(FaqCategoryDTO faqCatDTO) {

        log.info("adminPage POST /faq/remove...............");

        fs.removeFaqList(faqCatDTO.getFNo());
        fs.catRemove(faqCatDTO.getFNo());

        return "redirect:/adminPage/faq/categories";
    }

    @PostMapping("/faq/move")
    public String faqCatMovePOST(FaqCategoryDTO faqCatDTO,
                                 @RequestParam("newCatNo") int newCatNo) {

        log.info("adminPage POST faqCatMove.............");

        fs.moveCat(newCatNo, faqCatDTO.getFNo());
        return "redirect:/adminPage/faq/categories";
    }


/*
    |--------------------------------------------------------------------------
    | Book Category Management
    |--------------------------------------------------------------------------
    |
    | CRUD functionality for Book categories.
    | Functionality to move all books from one category to another.
    |
    |
    */


    @GetMapping("/book/categories")
    public void bookCatListGET(Model model) {

        log.info("adminPage GET BookCatList..............");

        List<CategoryDTO> catList = bs.catList();
        model.addAttribute("catList", catList);
    }

    @PostMapping("/book/register")
    public String bookCatRegisterPOST(@RequestParam("selectedMainCat") String selectedMainCat,
                                      @RequestParam("newCatName") String newCatName) {

        log.info("adminPage POST bookCatRegister...............");

        //만약 main category를 넣는다면
        if(selectedMainCat.isEmpty()) {
            log.info("New Cat: " + newCatName);
            String newCDcode = "0" + (Integer.parseInt(bs.getMaxCCode1()) + 1);
            log.info("new dcode: " + newCDcode);

            CategoryDTO catDTO = CategoryDTO.builder()
                    .cCode1(newCDcode)
                    .cDcode(newCDcode)
                    .cName(newCatName)
                    .build();
            bs.catRegister(catDTO);
        }
        //만약 sub category를 넣는다면
        else {
            log.info("selectedMainCat: " + selectedMainCat);

            String newCDcode = "";
            if(bs.getMaxCDcode(selectedMainCat) == null) {
                newCDcode = selectedMainCat + "01";
            } else {
                newCDcode = "0" + (Integer.parseInt(bs.getMaxCDcode(selectedMainCat)) + 1);
            }
            log.info("new dcode: " + newCDcode);

            CategoryDTO catDTO = CategoryDTO.builder()
                    .cCode1(selectedMainCat)
                    .cCode2(newCDcode.substring(newCDcode.length() - 2))
                    .cDcode(newCDcode)
                    .cName(newCatName)
                    .build();
            bs.catRegister(catDTO);
        }
        return "redirect:/adminPage/book/categories";
    }

    @PostMapping("/book/modify")
    public String bookCatModifyPOST(CategoryDTO catDTO,
                                    @RequestParam("newCategoryName") String newCategoryName) {

        log.info("adminPage POST bookCatModify.................");

        bs.setCatName(newCategoryName, catDTO.getCId());
        return "redirect:/adminPage/book/categories";
    }

    @PostMapping("/book/remove")
    public String bookCatRemovePOST(@RequestParam("cId") int cId) {

        log.info("adminPage POST bookCatRemove...............");

        CategoryDTO selectedCat = bs.readOneCat(cId);
        log.info("selectedCat: " + selectedCat);

        //main category를 삭제하는 경우
        if(selectedCat.getCDcode().length() == 2) {
            //main category + 연관된 모든 sub category 삭제
            bs.removeMainCatList(selectedCat.getCCode1());

            List<BookDTO> list = bs.getMainCatBooklist(selectedCat.getCCode1());
            log.info("list: " + list);

            for(BookDTO book : list) {
                //삭제된 책에 레코드 삽입
                DeletedBookDTO delBookDTO = DeletedBookDTO.builder()
                        .bTitle(book.getBTitle())
                        .dReason("CATEGORY DELETION")
                        .build();
                ds.register(delBookDTO);

                //책 active값 변경
                bs.modifyActiveStatus(book.getBNo());
            }
        }
        else {
            //sub category 삭제
            bs.removeSubCatList(selectedCat.getCDcode());

            List<BookDTO> list = bs.getSubCatBooklist(selectedCat.getCDcode());
            log.info("list: " + list);

            for(BookDTO book : list) {
                //삭제된 책에 레코드 삽입
                DeletedBookDTO delBookDTO = DeletedBookDTO.builder()
                        .bTitle(book.getBTitle())
                        .dReason("CATEGORY DELETION")
                        .build();
                ds.register(delBookDTO);

                //책 active값 변경
                bs.modifyActiveStatus(book.getBNo());
            }
        }
        return "redirect:/adminPage/book/categories";
    }

    @PostMapping("/book/move")
    public String bookCatMovePOST(CategoryDTO catDTO,
                                  @RequestParam("newCatDCode") String newCatDCode) {

        log.info("adminPage POST bookCatMove...............");

        log.info("catDTO: " + catDTO.toString());
        log.info("newCatId: " + newCatDCode);
        bs.moveCat(newCatDCode, catDTO.getCDcode());

        return "redirect:/adminPage/book/categories";
    }


/*
    |--------------------------------------------------------------------------
    | Password Change
    |--------------------------------------------------------------------------
    |
    | Password Change Functionality for Admin
    |
    |
    */



    @GetMapping("/changePw")
    public void changePwGET(Model model) {

        log.info("adminPage GET changePw..............");

        UserDTO userDTO = us.readOne(1);
        model.addAttribute("dto", userDTO);
    }

    @PostMapping("/changePw")
    public String changePwPOST(RedirectAttributes redirectAttributes,
                               @RequestParam("newPw") String newPw) {

        log.info("adminPage POST changePw..............");

        us.setPw(1, newPw);
        redirectAttributes.addFlashAttribute("result", "password changed");

        return "redirect:/adminPage/changePw";
    }
}

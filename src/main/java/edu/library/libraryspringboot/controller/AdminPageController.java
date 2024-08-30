package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.domain.Rental;
import edu.library.libraryspringboot.domain.VAdminHistory;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/adminPage")
@Log4j2
@RequiredArgsConstructor
public class AdminPageController {

    private final RenService rs;
    private final RenReqService rrs;
    private final UserService us;
    private final BookService bs;
    private final RtnReqService rts;
    private final ExtReqService es;
    private final BlklistService bls;

    @GetMapping("/home")
    public void homeGET(HttpServletRequest req, RedirectAttributes redirectAttributes, Model model) {

        model.addAttribute("renReqCnt", rrs.getAllReqCount());
        model.addAttribute("rtnReqCnt", rts.getAllReqCount());
        model.addAttribute("extReqCnt", es.getAllReqCount());
        model.addAttribute("hisCnt", rs.getAllCount());

        //대기중인 request 목록
        //대기중인 extension 목록
        //대여중인 리스트
        //history
        
        //멤버 리스트
        //비밀번호 변경
        
        //리스트에서 유저 이름 클릭하면 멤버 상세창으로
        
        //나중에 추가할 기능: 1대1문의
    }

    /*@GetMapping("/renReq")
    public void renReqGET(Model model) {

        List<Object[]> dtoList = rrs.ListAll();
        model.addAttribute("dto", dtoList);
    }*/
    @GetMapping("/renReq")
    public void renReqGET(PageRequestDTO pgReqDTO, Model model) {

        PageResponseDTO<VAdminRentalRequestDTO> respDTO = rrs.listAll(pgReqDTO);
        log.info(respDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/renReq")
    public String renReqPOST(@RequestParam("uId") String uId,
                             @RequestParam("bNo") int bNo,
                             @RequestParam("uNo") int uNo) {

        //rental 레코드 추가
        RentalDTO renDTO = RentalDTO.builder()
                .uId(uId)
                .bNo(bNo)
                .build();
        rs.register(renDTO);

        //renreq 의 renreq 값을 0으로
        rrs.setRenReq(uId, bNo);

        //book의 isRental값을 1로
        bs.setRenStatus(bNo);

        //user에 500포인트 추가
        UserDTO userDT0 = us.readOne(uNo);


        //(아직 개발안함)user 레벨변경

        return "redirect:/adminPage/renReq";
    }

    /*@GetMapping("/extReq")
    public void extReqGET(Model model) {

        List<Object[]> dtoList = es.ListAll();
        model.addAttribute("dto", dtoList);
    }*/

    @GetMapping("/extReq")
    public void extReqGET(PageRequestDTO pgReqDTO, Model model) {

        PageResponseDTO<VAdminExtensionRequestDTO> respDTO = es.listAll(pgReqDTO);
        log.info(respDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/extReq")
    public String extReqPOST(@RequestParam("uId") String uId,
                             @RequestParam("bNo") int bNo) {


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

    @GetMapping("/history")
    public void historyGET(PageRequestDTO pgReqDTO, Model model) {

        PageResponseDTO<VAdminHistoryDTO> respDTO = rs.adminHisList(pgReqDTO);
        log.info(respDTO);
        log.info(pgReqDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }
}

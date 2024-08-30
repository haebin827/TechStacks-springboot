package edu.library.libraryspringboot.controller;

import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/adminPage")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService us;
    private final DelUserService ds;
    private final BlklistService bs;
    private final RenService rs;
    private final RenReqService rrs;

    @GetMapping("/user/list")
    public void listGET(PageRequestDTO pgReqDTO, Model model) {

        PageResponseDTO<UserDTO> respDTO = us.list(pgReqDTO);
        log.info(respDTO);
        model.addAttribute("respDTO", respDTO);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/user/remove")
    public String removePOST(int uNo,
                             @RequestParam("uId") String uId,
                             PageRequestDTO pgReqDTO,
                             RedirectAttributes redirectAttributes) {

        log.info("POST user remove...................");

        UserDTO userDTO = us.readOne(uNo);
        log.info("uNo: " + uNo);

        int rentCnt = rs.getRentCount(uId);
        if(rentCnt > 0) {
            log.info("Rent Cnt by admin: " + rentCnt);
            redirectAttributes.addFlashAttribute("result", "Unavailable");
            return "redirect:/adminPage/user/read?uNo=" + uNo + "&" + pgReqDTO.getLink();
        }

        // 해당 uId와 관련된, 완료되지 않은 Rental request 모두삭제
        rrs.removeIncompleteList(uId);

        // Add to DeletedUser
        DeletedUserDTO delUserDTO = DeletedUserDTO.builder()
                .uId(uId)
                .dReason("BY ADMIN")
                .build();
        ds.register(delUserDTO);

        // Delete the user
        us.remove(uNo);

        redirectAttributes.addFlashAttribute("result", "Removed");
        redirectAttributes.addFlashAttribute("uId", userDTO.getUId());

        return "redirect:/adminPage/user/list";
    }

    @GetMapping({"/user/read", "/user/modify"})
    public void readGET(int uNo, PageRequestDTO pgReqDTO, Model model) {

        UserDTO userDTO = us.readOne(uNo);
        log.info("userDTO: " + userDTO);
        log.info("GetLink: " + pgReqDTO.getLink());


        // Fetch Blacklist Reason
        List<BlacklistDTO> dtoList = bs.getAllByUId(userDTO.getUId());

        model.addAttribute("dto", userDTO);
        model.addAttribute("blklistDTO", dtoList);
        model.addAttribute("pgReqDTO", pgReqDTO);
    }

    @PostMapping("/user/blklist")
    public String blklistPOST(int uNo,
                              @RequestParam("blklistReason") String blklistReason,
                             PageRequestDTO pgReqDTO) {

        log.info("User blklist Post........................: ");

        UserDTO userDTO = us.readOne(uNo);
        userDTO.setUIsBlacklist(true);
        log.info("userDTO: " + userDTO);

        us.modify(userDTO);

        // Add to blacklist
        BlacklistDTO blkDTO = BlacklistDTO.builder()
                .uId(userDTO.getUId())
                .blReason(blklistReason)
                .build();
        bs.register(blkDTO);

        return "redirect:/adminPage/user/list?" + pgReqDTO.getLink();
    }
}

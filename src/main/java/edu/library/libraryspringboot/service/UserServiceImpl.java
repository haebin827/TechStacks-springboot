package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.UserDTO;
import edu.library.libraryspringboot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository ur;
    private final ModelMapper mm;

    @Override
    public int register(UserDTO userDTO) {

        User user = mm.map(userDTO, User.class);
        int uNo = ur.save(user).getUNo();

        return uNo;
    }

    @Override
    public UserDTO readOne(int uNo) {

        Optional<User> result = ur.findById(uNo);
        User user = result.orElseThrow();
        UserDTO userDTO = mm.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void modify(UserDTO userDTO) {

        Optional<User> result = ur.findById(userDTO.getUNo());
        User user = result.orElseThrow();
        user.change(userDTO.getUId(), userDTO.getUPw(), userDTO.getUName(), userDTO.getUPhone(), userDTO.getUIsBlacklist());
        ur.save(user);
    }

    @Override
    public void setPoint(int uPoint, String uId) {

        ur.updatePoint(uPoint, uId);
    }

    @Override
    public void remove(int uNo) {
        ur.deleteById(uNo);
    }

    @Override
    public PageResponseDTO<UserDTO> list(PageRequestDTO pgReqDTO) {

        String[] types = pgReqDTO.getTypes();
        String keyword = pgReqDTO.getKeyword();
        Boolean check = pgReqDTO.getCheck();
        Pageable pageable = pgReqDTO.getPageable("uNo");

        Page<User> result = ur.searchAll(types, keyword, pageable, check);

        List<UserDTO> dtoList = result.getContent().stream()
                .map(user -> mm.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<UserDTO>withAll()
                .pageRequestDTO(pgReqDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public UserDTO verify(String uId, String uPw) {
        User user = ur.authenticate(uId, uPw);
        UserDTO userDTO = mm.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void setUuid(int uNo, String uUuid) {
        log.info("Updating UUID for user ID {} to {}", uNo, uUuid);
        ur.updateUuid(uNo, uUuid);
    }

    @Override
    public UserDTO getByUUID(String uUuid) {
        User user = ur.findByuUuid(uUuid);
        UserDTO userDTO = mm.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public long verifyPhone(String uPhone) {
        return ur.countByuPhone(uPhone);
    }

    @Override
    public int verifyId(String uId) {
        return ur.checkId(uId);
    }

    @Override
    public int checkAutomaticDeletionDate(String uId) {

        LocalDateTime date = ur.deleteUserAfterSixMonths(uId);

        if(date == null || uId.equals("admin")) {
            return -1;
        }

        LocalDateTime sixMonthsLater = date.plusMonths(6);
        log.info("LocalDateTime: " + sixMonthsLater);

        LocalDateTime now = LocalDateTime.now();
        long daysUntilSixMonths = ChronoUnit.DAYS.between(now, sixMonthsLater);
        log.info("Now: " + now);
        log.info("Days left: " + daysUntilSixMonths);
            if (daysUntilSixMonths < -1) return -2;
            if (daysUntilSixMonths == 6) return 7;
            if (daysUntilSixMonths == 5) return 6;
            if (daysUntilSixMonths == 4) return 5;
            if (daysUntilSixMonths == 3) return 4;
            if (daysUntilSixMonths == 2) return 3;
            if (daysUntilSixMonths == 1) return 2;
            if (daysUntilSixMonths == 0) return 1;
            if (daysUntilSixMonths == -1) return 0;

        return -1;
    }

    @Override
    public void setAcct(int uNo, String uName, String uPhone) {
        ur.updateAcct(uNo, uName, uPhone);
    }

    @Override
    public void setPw(int uNo, String uPw) {
        ur.updatePw(uNo, uPw);
    }

    @Override
    public void setLateRtn(String uId) {
        ur.updateLateRtn(uId);
    }

    @Override
    public void setLevel(String uLevel, String uId) {
        ur.updateLevel(uLevel, uId);
    }

    @Override
    public void setLevelUp(String uId) {
        ur.updateLevelUp(uId);
    }
}

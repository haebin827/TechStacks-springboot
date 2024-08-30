package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.UserDTO;
import org.springframework.data.repository.query.Param;

public interface UserService {

    int register(UserDTO userDTO);

    UserDTO readOne(int uNo);

    void modify(UserDTO userDTO);

    void remove(int uNo);

    PageResponseDTO<UserDTO> list(PageRequestDTO pgReqDTO);

    UserDTO verify(String uId, String uPw);

    void setUuid(int uNo, String uUuid);

    UserDTO getByUUID(String uUuid);

    int verifyPhone(String uPhone);

    int verifyId(String uId);

    int checkAutomaticDeletionDate(String uId);

    void setAcct(int uNo, String uName, String uPhone);

    void setPw(int uNo, String uPw);

    void setLateRtn(String uId);

    void setPoint(int uPoint, String uId);

    void setLevel(String uLevel, String uId);

    void setLevelUp(String uId);

/*
    List<MemberDTO> getAll();

 */
}
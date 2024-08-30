package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.UserDTO;
import edu.library.libraryspringboot.repository.search.UserSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class UserServiceTests {

    @Autowired
    private UserService us;


    /*@Test
    public void testRegister() {
        log.info(us.getClass().getName());

        UserDTO userDTO = UserDTO.builder()
                .uId("1234")
                .uPw("1234")
                .uName("haebin")
                .uPhone("4")
                .build();

        int uNo = us.register(userDTO);
        log.info("uNo: " + uNo);
    }*/



    /*@Test
    public void testModify() {
        UserDTO userDTO = UserDTO.builder()
                .uNo(2)
                .uId("MODIFY ID")
                .uPw("MODIFY PW")
                .uName("Modify")
                .uPhone("0000")
                .build();

        us.modify(userDTO);
        log.info("dto: " + userDTO);
    }*/

    @Test
    public void testReadOne() {
        log.info(us.readOne(2));
    }

    @Test
    public void testRemove() {
        us.remove(4);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("n")
                .keyword("e")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<UserDTO> responseDTO = us.list(pageRequestDTO);
        log.info("responseDTO: " + responseDTO);
    }

    @Test
    public void testCheckAutomaticDeletionDate() {
        int date = us.checkAutomaticDeletionDate("ID1");
        log.info("Date: " + date);
    }

    /*@Test
    public void testVerify() {
        UserDTO dto = us.verify("Haebin", "1234");
        log.info("dto: " + dto);
    }*/

    /*@Test
    public void testSetPoint() {
        UserDTO userDTO = us.readOne(3);
        us.setPoint(userDTO);
    }*/
}
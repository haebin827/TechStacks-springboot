package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.dto.VRentalRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class RenReqServiceTests {

    @Autowired
    private RenReqService rs;

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<VRentalRequestDTO> responseDTO = rs.list(pageRequestDTO, "1234");
        log.info("responseDTO: " + responseDTO);
    }

    @Test
    public void testSetRenReq() {
        rs.setRenReq("1234", 52);
    }
}

package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Blacklist;
import edu.library.libraryspringboot.dto.BlacklistDTO;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlklistService {

    int register(BlacklistDTO blkDTO);

    BlacklistDTO readOne(int blNo);

    void remove(int blNo);

    String getUId(int uNo);

    List<BlacklistDTO> getAllByUId(String uId);
}

package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Blacklist;
import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.dto.BlacklistDTO;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.repository.BlklistRepository;
import edu.library.libraryspringboot.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BlklistServiceImpl implements BlklistService {

    private final BlklistRepository br;
    private final ModelMapper mm;

    @Override
    public int register(BlacklistDTO blkDTO) {

        Blacklist blk = mm.map(blkDTO, Blacklist.class);
        int blId = br.save(blk).getBlId();

        return blId;
    }

    @Override
    public BlacklistDTO readOne(int blId) {

        Optional<Blacklist> result = br.findById(blId);
        Blacklist blk = result.orElseThrow();
        BlacklistDTO blkDTO = mm.map(blk, BlacklistDTO.class);
        return blkDTO;
    }

    @Override
    public void remove(int blId) {
        br.deleteById(blId);
    }

    @Override
    public String getUId(int uNo) {
        String blReason = br.findUId(uNo);
        if (blReason == null) {
            return null;
        }
        return blReason;
    }

    @Override
    public List<BlacklistDTO> getAllByUId(String uId) {
        List<Blacklist> list = br.selectAllByUId(uId);
        List<BlacklistDTO> dtoList = list.stream()
                .map(blklist -> mm.map(blklist, BlacklistDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
}

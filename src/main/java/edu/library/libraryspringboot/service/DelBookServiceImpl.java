package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.DeletedBook;
import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.dto.DeletedBookDTO;
import edu.library.libraryspringboot.dto.DeletedUserDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.repository.DelBookRepository;
import edu.library.libraryspringboot.repository.DelUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DelBookServiceImpl implements DelBookService {

    private final DelBookRepository dr;
    private final ModelMapper mm;

    @Override
    public int register(DeletedBookDTO delBookDTO) {

        DeletedBook delBook = mm.map(delBookDTO, DeletedBook.class);
        int dId = dr.save(delBook).getDId();

        return dId;
    }

    @Override
    public DeletedBookDTO readOne(int dId) {

        Optional<DeletedBook> result = dr.findById(dId);
        DeletedBook delBook = result.orElseThrow();
        DeletedBookDTO delBookDTO = mm.map(delBook, DeletedBookDTO.class);
        return delBookDTO;
    }

    @Override
    public PageResponseDTO<DeletedBookDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("dId");

        Page<DeletedBook> result = dr.searchAll(types, keyword, pageable);

        List<DeletedBookDTO> dtoList = result.getContent().stream()
                .map(delBook -> mm.map(delBook, DeletedBookDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<DeletedBookDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}

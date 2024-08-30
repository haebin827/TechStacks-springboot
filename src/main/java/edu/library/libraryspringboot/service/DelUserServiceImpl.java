package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.BookRepository;
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
public class DelUserServiceImpl implements DelUserService {

    private final DelUserRepository dr;
    private final ModelMapper mm;

    @Override
    public int register(DeletedUserDTO delUserDTO) {

        DeletedUser delUser = mm.map(delUserDTO, DeletedUser.class);
        int dId = dr.save(delUser).getDId();

        return dId;
    }

    @Override
    public DeletedUserDTO readOne(int dId) {

        Optional<DeletedUser> result = dr.findById(dId);
        DeletedUser delUser = result.orElseThrow();
        DeletedUserDTO delUserDTO = mm.map(delUser, DeletedUserDTO.class);
        return delUserDTO;
    }

    @Override
    public PageResponseDTO<DeletedUserDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("dId");

        Page<DeletedUser> result = dr.searchAll(types, keyword, pageable);

        List<DeletedUserDTO> dtoList = result.getContent().stream()
                .map(delUser -> mm.map(delUser, DeletedUserDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<DeletedUserDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public DeletedUserDTO readOneByUId(String uId) {
        DeletedUser delUser = dr.selectOneByUId(uId);
        DeletedUserDTO delUserDTO = mm.map(delUser, DeletedUserDTO.class);
        return delUserDTO;
    }
}

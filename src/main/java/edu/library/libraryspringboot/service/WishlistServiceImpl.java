package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.domain.VAdminReturnRequest;
import edu.library.libraryspringboot.domain.VWishlist;
import edu.library.libraryspringboot.domain.Wishlist;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.VWishlistRepository;
import edu.library.libraryspringboot.repository.WishlistRepository;
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
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wr;
    private final VWishlistRepository v_wr;
    private final ModelMapper mm;

    @Override
    public void register(WishlistDTO wListDTO) {

        Wishlist wList = mm.map(wListDTO, Wishlist.class);
        wr.save(wList);
    }

    @Override
    public WishlistDTO readOne(int wId) {

        Optional<Wishlist> result = wr.findById(wId);
        Wishlist wList = result.orElseThrow();
        WishlistDTO wListDTO = mm.map(wList, WishlistDTO.class);
        return wListDTO;
    }

    @Override
    public void remove(String uId, int bNo) {
        wr.deleteWList(uId, bNo);
    }

    @Override
    public int getWListCnt(String uId, int bNo) {
        return wr.findWListCnt(uId, bNo);
    }

    @Override
    public PageResponseDTO<VWishlistDTO> listAll(PageRequestDTO pageRequestDTO, String uId) {

        Pageable pageable = pageRequestDTO.getPageable();

        Page<VWishlist> result = v_wr.searchAll(pageable, uId);

        List<VWishlistDTO> dtoList = result.getContent().stream()
                .map(vWList -> mm.map(vWList, VWishlistDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<VWishlistDTO> withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}

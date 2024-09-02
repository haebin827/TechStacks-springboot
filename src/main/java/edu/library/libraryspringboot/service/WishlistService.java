package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;
import org.springframework.data.repository.query.Param;

public interface WishlistService {

    void register(WishlistDTO wListDTO);

    WishlistDTO readOne(int wId);

    void remove(String uId, int bNo);

    int getWListCnt(String uId, int bNo);

    PageResponseDTO<VWishlistDTO> listAll(PageRequestDTO pageRequestDTO, String uId);
}

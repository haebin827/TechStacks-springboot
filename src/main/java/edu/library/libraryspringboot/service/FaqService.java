package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;

import java.util.List;

public interface FaqService {

    int register(FaqDTO faqDTO);

    FaqDTO readOne(int fId);

    void modify(FaqDTO faqDTO);

    void remove(int fId);

    PageResponseDTO<FaqDTO> list(PageRequestDTO pageRequestDTO);

    List<FaqCategoryDTO> catList();
}

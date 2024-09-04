package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FaqService {

    int register(FaqDTO faqDTO);

    void catRegister(FaqCategoryDTO faqCatDTO);

    FaqDTO readOne(int fId);

    void modify(FaqDTO faqDTO);

    void setCatName(String fName,int fNo);

    void remove(int fId);

    void catRemove(int fNo);

    PageResponseDTO<FaqDTO> list(PageRequestDTO pageRequestDTO);

    List<FaqCategoryDTO> catList();

    void removeFaqList(int fCategory);

    void moveCat(int fCat, int fPrevCat);
}

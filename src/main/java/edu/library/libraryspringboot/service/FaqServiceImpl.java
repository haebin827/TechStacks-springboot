package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Faq;
import edu.library.libraryspringboot.domain.FaqCategory;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.BookRepository;
import edu.library.libraryspringboot.repository.FaqCatRepository;
import edu.library.libraryspringboot.repository.FaqRepository;
import edu.library.libraryspringboot.repository.search.FaqSearch;
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
public class FaqServiceImpl implements FaqService {

    private final FaqRepository fr;
    private final ModelMapper mm;
    private final FaqCatRepository cr;

    @Override
    public int register(FaqDTO faqDTO) {

        Faq faq = mm.map(faqDTO, Faq.class);
        int fId = fr.save(faq).getFId();

        return fId;
    }

    @Override
    public FaqDTO readOne(int fId) {

        Optional<Faq> result = fr.findById(fId);
        Faq faq = result.orElseThrow();
        FaqDTO faqDTO = mm.map(faq, FaqDTO.class);
        return faqDTO;
    }

    @Override
    public void modify(FaqDTO faqDTO) {
        Optional<Faq> result = fr.findById(faqDTO.getFId());
        Faq faq = result.orElseThrow();
        faq.change(faqDTO.getFQuestion(), faqDTO.getFAnswer(), faqDTO.getFCategory());

        fr.save(faq);
    }

    @Override
    public void remove(int fId) {
        fr.deleteById(fId);
    }

    @Override
    public PageResponseDTO<FaqDTO> list(PageRequestDTO pageRequestDTO) {

        String word = pageRequestDTO.getWord();
        int group = pageRequestDTO.getGroup();
        Pageable pageable = pageRequestDTO.getPageable("fId");

        Page<Faq> result = fr.searchAll(pageable, word, group);

        List<FaqDTO> dtoList = result.getContent().stream()
                .map(faq -> mm.map(faq, FaqDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<FaqDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public List<FaqCategoryDTO> catList() {
        List<FaqCategoryDTO> list = cr.findAll().stream()
                .map(cat -> mm.map(cat, FaqCategoryDTO.class))
                .collect(Collectors.toList());

        return list;
    }
}

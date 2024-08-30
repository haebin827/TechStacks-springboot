package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.repository.BookRepository;
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
public class BookServiceImpl implements BookService {

    private final BookRepository br;
    private final ModelMapper mm;

    @Override
    public int register(BookDTO bookDTO) {

        Book book = mm.map(bookDTO, Book.class);
        int bNo = br.save(book).getBNo();

        return bNo;
    }

    @Override
    public BookDTO readOne(int bNo) {

        Optional<Book> result = br.findById(bNo);
        Book book = result.orElseThrow();
        BookDTO bookDTO = mm.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public void modify(BookDTO bookDTO) {

        Optional<Book> result = br.findById(bookDTO.getBNo());
        Book book = result.orElseThrow();
        book.change(bookDTO.getBTitle(), bookDTO.getBAuthor(), bookDTO.getBIsbn(), bookDTO.getBYear(), bookDTO.getBPublisher());
        br.save(book);
    }

    @Override
    public void remove(int bNo) {
        br.deleteById(bNo);
    }

    @Override
    public PageResponseDTO<BookDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bNo");
        Boolean check = pageRequestDTO.getCheck();

        Page<Book> result = br.searchAll(types, keyword, pageable, check);

        List<BookDTO> dtoList = result.getContent().stream()
                .map(book -> mm.map(book, BookDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BookDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void modifyActiveStatus(int bNo) {
        br.updateActiveStatus(bNo);
    }

    @Override
    public void setRenStatus(int bNo) {
        br.updateRenStatus(bNo);
    }

    @Override
    public void setRtnStatus(int bNo, String bCondition) {
        br.updateRtnStatus(bNo, bCondition);
    }
}

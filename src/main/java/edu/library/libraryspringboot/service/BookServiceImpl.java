package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.Category;
import edu.library.libraryspringboot.dto.*;
import edu.library.libraryspringboot.repository.BookRepository;
import edu.library.libraryspringboot.repository.CatRepository;
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
    private final CatRepository cr;
    private final ModelMapper mm;

    @Override
    public void register(BookDTO bookDTO) {

        Book book = mm.map(bookDTO, Book.class);
        br.save(book).getBNo();
    }

    @Override
    public BookDTO readOne(int bNo) {

        Optional<Book> result = br.findById(bNo);
        Book book = result.orElseThrow();
        BookDTO bookDTO = mm.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public CategoryDTO readOneCat(int cId) {
        Optional<Category> result = cr.findById(cId);
        Category category = result.orElseThrow();
        CategoryDTO categoryDTO = mm.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public void modify(BookDTO bookDTO) {

        Optional<Book> result = br.findById(bookDTO.getBNo());
        Book book = result.orElseThrow();
        book.change(bookDTO.getBTitle(), bookDTO.getBAuthor(), bookDTO.getBIsbn(), bookDTO.getBYear(), bookDTO.getBPublisher(), bookDTO.getBCategory(), bookDTO.getBCondition());
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
        String cat = pageRequestDTO.getCat();
        Pageable pageable = pageRequestDTO.getPageable("bNo");
        Boolean check = pageRequestDTO.getCheck();

        Page<Book> result = br.searchAll(types, keyword, pageable, check, cat);

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

    @Override
    public List<CategoryDTO> catList() {
        List<CategoryDTO> list = cr.findAll().stream()
                .map(cat -> mm.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public void removeMainCatList(String cCode1) {
        cr.deleteBycCode1(cCode1);
    }

    @Override
    public void removeSubCatList(String cDcode) {
        cr.deleteBycDcode(cDcode);
    }

    @Override
    public void catRemove(int cId) {
        cr.deleteById(cId);
    }

    @Override
    public String getMaxCCode1() {
        return cr.findMaxCCode1();
    }

    @Override
    public String getMaxCDcode(String selectedMainCat) {
        return cr.findMaxCDcode(selectedMainCat);
    }

    @Override
    public void catRegister(CategoryDTO catDTO) {

        Category cat = mm.map(catDTO, Category.class);
        cr.save(cat);
    }

    /*@Override
    public void removeBooksByMainCat(String cCode1) {
        br.deleteBooksByMainCat(cCode1);
    }*/

    /*@Override
    public void removeBooksBySubCat(String cDcode) {
        br.deleteBooksBySubCat(cDcode);
    }*/

    @Override
    public List<BookDTO> getMainCatBooklist(String cCode1) {
        List<BookDTO> list = br.findBybCategoryStartingWith(cCode1).stream()
                .map(book -> mm.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<BookDTO> getSubCatBooklist(String cDcode) {
        List<BookDTO> list = br.findBybCategory(cDcode).stream()
                .map(book -> mm.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void setCatName(String cName, int cId) {
        cr.updateCatName(cName, cId);
    }

    @Override
    public void moveCat(String bCat, String bPrevCat) {
        br.changeCat(bCat, bPrevCat);
    }

    @Override
    public List<BookDTO> getTop20RecentBooks() {
        List<BookDTO> list = br.findTop20RecentBooks().stream()
                .map(book -> mm.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return list;
    }
}

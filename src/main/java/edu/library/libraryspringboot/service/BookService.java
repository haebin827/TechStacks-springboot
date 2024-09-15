package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.CategoryDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {

    int register(BookDTO bookDTO);

    BookDTO readOne(int bNo);

    CategoryDTO readOneCat(int cId);

    void modify(BookDTO bookDTO);

    void remove(int bNo);

    PageResponseDTO<BookDTO> list(PageRequestDTO pageRequestDTO);

    void modifyActiveStatus(int bNo);

    void setRenStatus(int bNo);

    void setRtnStatus(int bNo, String bCondition);

    List<CategoryDTO> catList();

    void removeMainCatList(String cCode1);

    void removeSubCatList(String cDcode);

    void catRemove(int cId);

    String getMaxCCode1();

    String getMaxCDcode(String selectedMainCat);

    void catRegister(CategoryDTO catDTO);

    /*void removeBooksByMainCat(String cCode1);*/

    /*void removeBooksBySubCat(String cDcode);*/

    List<BookDTO> getMainCatBooklist(String cCode1);

    List<BookDTO> getSubCatBooklist(String cDcode);

    void setCatName(String cName, int cId);

    void moveCat(String bCat, String bPrevCat);

}

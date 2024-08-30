package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.CategoryDTO;

import java.util.List;

public interface CatService {

    int register(CategoryDTO catDTO);

    CategoryDTO readOne(int cId);

    void modify(CategoryDTO catDTO);

    void remove(int cId);

    List<CategoryDTO> catList();
}

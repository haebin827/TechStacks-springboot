package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Category;
import edu.library.libraryspringboot.dto.CategoryDTO;
import edu.library.libraryspringboot.repository.CatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CatServiceImpl implements CatService {

    private final CatRepository cr;
    private final ModelMapper mm;

    @Override
    public int register(CategoryDTO catDTO) {

        Category cat = mm.map(catDTO, Category.class);
        int bNo = cr.save(cat).getCId();

        return bNo;
    }

    @Override
    public CategoryDTO readOne(int cId) {

        Optional<Category> result = cr.findById(cId);
        Category cat = result.orElseThrow();
        CategoryDTO catDTO = mm.map(cat, CategoryDTO.class);
        return catDTO;
    }

    @Override
    public void modify(CategoryDTO catDTO) {

        Optional<Category> result = cr.findById(catDTO.getCId());
        Category cat = result.orElseThrow();
        cat.change(catDTO.getCName(), catDTO.getCCode1(), catDTO.getCCode2(), catDTO.getCDcode());
        cr.save(cat);
    }

    @Override
    public void remove(int cId) {
        cr.deleteById(cId);
    }

    @Override
    public List<CategoryDTO> catList() {
        List<CategoryDTO> list = cr.findAll().stream()
                .map(cat -> mm.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());

        return list;
    }
}

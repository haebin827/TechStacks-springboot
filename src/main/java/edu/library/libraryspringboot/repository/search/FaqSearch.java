package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqSearch {

    Page<Faq> searchAll(Pageable pageable, String word, int group);
}

package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.VAdminExtensionRequest;
import edu.library.libraryspringboot.domain.VAdminHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VAdminHisSearch {

    Page<VAdminHistory> searchAll(String[] types, String keyword, Pageable pageable, String choose);
}

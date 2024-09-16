package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.repository.search.DelUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelUserRepository extends JpaRepository<DeletedUser, Integer>, DelUserSearch {

    DeletedUser findByuId(String uId);
}

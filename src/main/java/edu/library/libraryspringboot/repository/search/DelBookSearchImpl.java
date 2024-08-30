package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.DeletedBook;
import edu.library.libraryspringboot.domain.DeletedUser;
import edu.library.libraryspringboot.domain.QDeletedBook;
import edu.library.libraryspringboot.domain.QDeletedUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class DelBookSearchImpl extends QuerydslRepositorySupport implements DelBookSearch {

    public DelBookSearchImpl() {
        super(DeletedBook.class);
    }

    @Override
    public Page<DeletedBook> searchAll(String[] types, String keyword, Pageable pageable) {

        QDeletedBook delBook = QDeletedBook.deletedBook;
        JPQLQuery<DeletedBook> query = from(delBook);

        if ((types != null && types.length > 0) && !keyword.isEmpty()) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(delBook.bTitle.contains(keyword));
                        break;
                }
            } //end for
            query.where(booleanBuilder);
        } // end if

        //bNo > 0
        query.where(delBook.dId.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<DeletedBook> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}

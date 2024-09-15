package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.QBook;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

@Log4j2
public class BookSearchImpl extends QuerydslRepositorySupport implements BookSearch {

    public BookSearchImpl() {
        super(Book.class);
    }

    @Override
    public Page<Book> search1(Pageable pageable) {

        QBook book = QBook.book; //Q도메인 객체
        JPQLQuery<Book> query = from(book);
        query.where(book.bNo.eq(1)); // where r_id like...

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Book> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Book> searchAll(String[] types, String keyword, Pageable pageable, Boolean check, String cat) {

        QBook book = QBook.book;
        JPQLQuery<Book> query = from(book);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 검색 조건 추가
        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {
            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(book.bTitle.contains(keyword));
                        break;
                    case "a":
                        booleanBuilder.or(book.bAuthor.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(book.bIsbn.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // 기본 조건 추가 (bNo > 0)
        query.where(book.bNo.gt(0));

        // 카테고리 조건 추가
        if (!cat.equals("0")) {
            // If category is selected, show all books where the category starts with the selected parent category
            query.where(book.bCategory.startsWith(cat));
        }

        // 현재 active 된 책만 보여주기
        query.where(book.bIsActive.eq(true));

        // check가 true일 경우 렌탈 조건 추가
        if (Boolean.TRUE.equals(check)) {
            query.where(book.bIsRental.eq(false));
        }

        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(book.bNo.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<Book> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }
}

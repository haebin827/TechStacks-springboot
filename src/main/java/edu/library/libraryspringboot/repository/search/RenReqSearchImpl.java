package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.RentalRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RenReqSearchImpl extends QuerydslRepositorySupport implements RenReqSearch {

    public RenReqSearchImpl() {
        super(RentalRequest.class);
    }

    /*@Override
    public Page<RentalRequest> searchAll(String[] types, String keyword, Pageable pageable) {

        QBook book = QBook.book;
        JPQLQuery<Book> query = from(book);

        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

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

        // 현재 active 된 책만 보여주기
        query.where(book.bIsActive.eq(true));

        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(book.bNo.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<Book> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        //return new PageImpl<>(list, pageable, count);
    }*/
}

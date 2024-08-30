package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.library.libraryspringboot.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class VHistorySearchImpl extends QuerydslRepositorySupport implements VHistorySearch {

    public VHistorySearchImpl() {
        super(VHistory.class);
    }

    @Override
    public Page<VHistory> searchAll(String[] types, String keyword, Pageable pageable, Boolean check, String uId) {

        // RentalRequestView 엔티티를 기준으로 JPQLQuery 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        QVHistory vHistory = QVHistory.vHistory;
        JPQLQuery<VHistory> query = queryFactory.selectFrom(vHistory);

        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(vHistory.bTitle.contains(keyword));
                        break;
                    case "a":
                        booleanBuilder.or(vHistory.bAuthor.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(vHistory.bIsbn.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // 기본 조건 추가 (bNo > 0)
        query.where(vHistory.bNo.gt(0));

        query.where(vHistory.uId.eq(uId));

        // check가 true일 경우 현재 active 된 책만 보여주기
        if (Boolean.TRUE.equals(check)) {
            query.where(vHistory.bIsActive.eq(true));
        }

        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(vHistory.rRentalDate.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<VHistory> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }
}

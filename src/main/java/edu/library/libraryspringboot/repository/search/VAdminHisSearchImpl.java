package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.Book;
import edu.library.libraryspringboot.domain.QBook;
import edu.library.libraryspringboot.domain.QVAdminHistory;
import edu.library.libraryspringboot.domain.VAdminHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class VAdminHisSearchImpl extends QuerydslRepositorySupport implements VAdminHisSearch {

    public VAdminHisSearchImpl() {
        super(VAdminHistory.class);
    }

    @Override
    public Page<VAdminHistory> searchAll(String[] types, String keyword, Pageable pageable, String choose) {

        QVAdminHistory his = QVAdminHistory.vAdminHistory;
        JPQLQuery<VAdminHistory> query = from(his);

        // 조건 검색 처리
        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(his.bTitle.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(his.uId.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // 라디오 버튼 필터링
        if (choose != null && !choose.isEmpty()) {
            if (choose.equals("Currently Rented")) {
                query.where(his.rIsReturned.eq(false));
            } else if (choose.equals("Returned Items")) {
                query.where(his.rIsReturned.eq(true));
            }
            // "Show All" 이 선택된 경우는 필터를 추가하지 않음
        }

        // 정렬 조건 추가 (rRentalDate 기준 내림차순)
        query.orderBy(his.rRentalDate.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<VAdminHistory> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }

}

package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.library.libraryspringboot.domain.QVAdminRentalRequest;
import edu.library.libraryspringboot.domain.QVAdminReturnRequest;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.domain.VAdminReturnRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class VAdminRtnReqSearchImpl extends QuerydslRepositorySupport implements VAdminRtnReqSearch{

    public VAdminRtnReqSearchImpl() {
        super(VAdminReturnRequest.class);
    };

    @Override
    public Page<VAdminReturnRequest> searchAll(String[] types, String keyword, Pageable pageable) {

        // RentalRequestView 엔티티를 기준으로 JPQLQuery 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        QVAdminReturnRequest vAdminRtnReq = QVAdminReturnRequest.vAdminReturnRequest;
        JPQLQuery<VAdminReturnRequest> query = queryFactory.selectFrom(vAdminRtnReq);

        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(vAdminRtnReq.bTitle.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(vAdminRtnReq.uId.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // check가 true일 경우 현재 active 된 책만 보여주기
        /*if (Boolean.TRUE.equals(check)) {
            query.where(vHistory.bIsActive.eq(true));
        }*/

        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(vAdminRtnReq.rReqDate.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<VAdminReturnRequest> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }
}

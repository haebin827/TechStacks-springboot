package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.library.libraryspringboot.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class VRenReqSearchImpl extends QuerydslRepositorySupport implements VRenReqSearch {

    public VRenReqSearchImpl() {
        super(VRentalRequest.class);
    }

    @Override
    public Page<VRentalRequest> searchAll(String[] types, String keyword, Pageable pageable, String uId) {

        // RentalRequestView 엔티티를 기준으로 JPQLQuery 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        QVRentalRequest vRentalRequest = QVRentalRequest.vRentalRequest;
        JPQLQuery<VRentalRequest> query = queryFactory.selectFrom(vRentalRequest);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        query.where(vRentalRequest.u_id.eq(uId));

        query.orderBy(vRentalRequest.r_req_date.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);

        // 쿼리 실행 및 결과 반환
        List<VRentalRequest> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

}

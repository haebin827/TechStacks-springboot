package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.library.libraryspringboot.domain.QVAdminRentalRequest;
import edu.library.libraryspringboot.domain.QVHistory;
import edu.library.libraryspringboot.domain.VAdminRentalRequest;
import edu.library.libraryspringboot.domain.VHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class VAdminRenReqSearchImpl extends QuerydslRepositorySupport implements VAdminRenReqSearch{

    public VAdminRenReqSearchImpl() {
        super(VAdminRentalRequest.class);
    };

    @Override
    public Page<VAdminRentalRequest> searchAll(String[] types, String keyword, Pageable pageable, Boolean check) {

        // RentalRequestView 엔티티를 기준으로 JPQLQuery 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        QVAdminRentalRequest vAdminRenReq = QVAdminRentalRequest.vAdminRentalRequest;
        JPQLQuery<VAdminRentalRequest> query = queryFactory.selectFrom(vAdminRenReq);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if ((types != null && types.length > 0) && StringUtils.hasText(keyword)) {

            for(String type: types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(vAdminRenReq.bTitle.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(vAdminRenReq.uId.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // check가 true일 경우 현재 가능한 요청만 보여주기
        if (Boolean.TRUE.equals(check)) {
            booleanBuilder.and(vAdminRenReq.bIsRental.isFalse())
                    .and(vAdminRenReq.rentalCount.lt(3));

            query.where(booleanBuilder);
        }

        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(vAdminRenReq.rReqDate.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<VAdminRentalRequest> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }
}

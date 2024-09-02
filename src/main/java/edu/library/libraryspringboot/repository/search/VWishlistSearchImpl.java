package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.library.libraryspringboot.domain.QVRentalRequest;
import edu.library.libraryspringboot.domain.QVWishlist;
import edu.library.libraryspringboot.domain.VRentalRequest;
import edu.library.libraryspringboot.domain.VWishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class VWishlistSearchImpl extends QuerydslRepositorySupport implements VWishlistSearch{

    public VWishlistSearchImpl() {
        super(VWishlist.class);
    }

    @Override
    public Page<VWishlist> searchAll(Pageable pageable, String uId) {

        // RentalRequestView 엔티티를 기준으로 JPQLQuery 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        QVWishlist vWList = QVWishlist.vWishlist;
        JPQLQuery<VWishlist> query = queryFactory.selectFrom(vWList);

        query.where(vWList.uId.eq(uId));
        query.orderBy(vWList.id.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);

        // 쿼리 실행 및 결과 반환
        List<VWishlist> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}

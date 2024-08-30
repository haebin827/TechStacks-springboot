package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class NotifSearchImpl extends QuerydslRepositorySupport implements NotifSearch {

    public NotifSearchImpl() {
        super(Notification.class);
    }

    @Override
    public Page<Notification> search1(Pageable pageable) {

        QNotification notif = QNotification.notification; //Q도메인 객체
        JPQLQuery<Notification> query = from(notif);
        query.where(notif.nNo.eq(1)); // where r_id like...
        List<Notification> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Notification> searchAll(String keyword, Pageable pageable) {

        QNotification notif = QNotification.notification;
        JPQLQuery<Notification> query = from(notif);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (StringUtils.hasText(keyword)) {
            booleanBuilder.or(notif.nTitle.contains(keyword));
            query.where(booleanBuilder);
        }
        // 정렬 조건 추가 (bNo 기준 내림차순)
        query.orderBy(notif.nRegDate.desc());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        List<Notification> list = query.fetch();
        long count = query.fetchCount();

        // 결과 반환
        return new PageImpl<>(list, pageable, count);
    }
}

package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class UserSearchImpl extends QuerydslRepositorySupport implements UserSearch {

    public UserSearchImpl() {
        super(User.class);
    }

    @Override
    public Page<User> search1(Pageable pageable) {

        QUser user = QUser.user;
        JPQLQuery<User> query = from(user);
        query.where(user.uName.contains("e"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<User> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<User> searchAll(String[] types, String keyword, Pageable pageable, Boolean check) {

        QUser user = QUser.user;
        JPQLQuery<User> query = from(user);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if ((types != null && types.length > 0) && keyword != null && !keyword.isEmpty()) {
            for(String type: types) {
                switch(type) {
                    case "n":
                        booleanBuilder.or(user.uName.contains(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(user.uId.contains(keyword));
                        break;
                    case "p":
                        booleanBuilder.or(user.uPhone.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        // mNo > 0
        query.where(user.uNo.gt(1));

        // check가 true일 경우 블랙리스트 조건 추가
        if (Boolean.TRUE.equals(check)) {
            query.where(user.uIsBlacklist.eq(true));
        }

        // order by uRegDate desc
        query.orderBy(user.uRegDate.desc());

        // paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<User> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }


}

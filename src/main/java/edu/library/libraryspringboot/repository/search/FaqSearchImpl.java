package edu.library.libraryspringboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import edu.library.libraryspringboot.domain.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

@Log4j2
public class FaqSearchImpl extends QuerydslRepositorySupport implements FaqSearch {

    public FaqSearchImpl() {
        super(Faq.class);
    }

    @Override
    public Page<Faq> searchAll(Pageable pageable, String word, int group) {
        QFaq faq = QFaq.faq;
        JPQLQuery<Faq> query = from(faq);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (StringUtils.hasText(word) && !word.isEmpty()) {
            booleanBuilder.or(faq.fQuestion.contains(word));
            query.where(booleanBuilder);
        }

        // 그룹 필터링 조건 추가 (group이 0보다 크면 해당 조건 추가)
        if (group > 0) {
            booleanBuilder.and(faq.fCategory.eq(group));
        }

        query.where(booleanBuilder);

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Faq> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}


package edu.library.libraryspringboot.repository.search;

import edu.library.libraryspringboot.domain.Rental;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RenSearchImpl extends QuerydslRepositorySupport implements RenSearch {

    public RenSearchImpl() {
        super(Rental.class);
    }


    //조인 걸어서 search해야함 (bNo -> bTitle)

    /*@Override
    public Page<Rental> searchAll(String[] types, String keyword, Pageable pageable) {

        QRental ren = QRental.rental;
        JPQLQuery<Rental> query = from(ren);

        if ((types != null && types.length > 0) && !keyword.isEmpty()) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type: types) {
                switch(type) {
                    case "i":
                        booleanBuilder.or(ren.uId.contains(keyword));
                        break;
                    case "a":
                        booleanBuilder.or(ren.bNo.eq(keyword));
                        break;
                    case "i":
                        booleanBuilder.or(book.bIsbn.contains(keyword));
                        break;
                }
            } //end for
            query.where(booleanBuilder);
        } // end if

        //bNo > 0
        query.where(book.bNo.gt(0));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Book> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }*/

}

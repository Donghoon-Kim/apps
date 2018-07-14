package com.kb.bookapp.repository.custom;

import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.business.component.UserApi.QUserBook;
import com.kb.bookapp.business.component.UserApi.UserBook;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.kb.bookapp.business.component.UserApi.QUserBook.userBook;

@Repository
public class BookRepositoryImpl extends QuerydslRepositorySupport implements CustomBookRepository {
    public BookRepositoryImpl() {
        super(UserBook.class);
    }

    public Page<UserBook> findBySearchCondition(SearchCondition searchCondition, Pageable pageable) {
        JPQLQuery<UserBook> query = from(userBook);
        if (StringUtils.isEmpty(searchCondition.getQuery())) {
            throw new IllegalArgumentException("query 전달인자를 포함해야함.");
        }

        String queryString = searchCondition.getQuery();
        String sort = searchCondition.getSort();
        String target = searchCondition.getTarget();

        switch (sort) {
            case "accuracy":
            default:
                query = query.orderBy(userBook.accuracy.desc());
                break;
            case "recency":
                query = query.orderBy(userBook.bookPrimaryInfo().regiDatetime.desc());
                break;
            case "sales":
                query = query.orderBy(userBook.sales.desc());
                break;
        }
        switch (target) {
            case "all":
            default:
                query = query.where(userBook.bookPrimaryInfo().title.contains(queryString)
                        .or(userBook.bookPrimaryInfo().isbn.contains(queryString))
                        .or(userBook.keyword.contains(queryString))
                        .or(userBook.bookPrimaryInfo().contents.contains(queryString))
                        .or(userBook.overview.contains(queryString))
                        .or(userBook.bookPrimaryInfo().publisher.contains(queryString)));
                break;
            case "title":
                query = query.where(userBook.bookPrimaryInfo().title.contains(queryString));
                break;
            case "isbn":
                query = query.where(userBook.bookPrimaryInfo().isbn.contains(queryString));
                break;
            case "keyword":
                query = query.where(userBook.keyword.contains(queryString));
                break;
            case "contents":
                query = query.where(userBook.bookPrimaryInfo().contents.contains(queryString));
                break;
            case "overview":
                query = query.where(userBook.overview.contains(queryString));
                break;
            case "publisher":
                query = query.where(userBook.bookPrimaryInfo().publisher.contains(queryString));
                break;
        }

        QueryResults<UserBook> userBookQueryResults = getQuerydsl()
                .applyPagination(pageable, query)
                .fetchResults();

        return new PageImpl<>(userBookQueryResults.getResults(), pageable, userBookQueryResults.getTotal());
    }
}

package com.kb.bookapp.business.service;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.component.ApiInterface.ComponentInterface;
import com.kb.bookapp.business.component.ApiInterface.SearchBookResult;
import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.business.component.UserApi.UserBook;
import com.kb.bookapp.business.domain.SearchApi;
import com.kb.bookapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BookService<B extends BookInterface> {
    @Autowired
    private LinkedHashMap<Long, ComponentInterface> searchApiMap;

    private ComponentInterface getComponentInstance(SearchApi searchApi){
        ComponentInterface componentInterface = searchApiMap.get(searchApi.getSearchApiIdx());
        if(componentInterface == null){
            throw new IllegalArgumentException("존재하지 않는 API 번호입니다.");
        }

        return componentInterface;
    }


    public SearchBookResult search(SearchApi searchApi, final HashMap<String, String> paramMap) {
        ComponentInterface componentInstance = getComponentInstance(searchApi);

        String size = paramMap.getOrDefault("size", "10");
        String page = paramMap.getOrDefault("page", "1");
        String query = paramMap.getOrDefault("query", "");
        String target = paramMap.getOrDefault("target", "");
        String sort = paramMap.getOrDefault("sort", "accuracy");
        String categoryIdx = paramMap.getOrDefault("category", "0");

        SearchCondition searchCondition = new SearchCondition(
                Integer.valueOf(page),
                Integer.valueOf(size),
                searchApi.getSearchApiIdx(),
                query,
                target,
                sort,
                Long.valueOf(categoryIdx)
        );

        return componentInstance.requestSearch(searchCondition);
    }

    public BookInterface save(B book, SearchApi searchApi){
        ComponentInterface componentInstance = getComponentInstance(searchApi);
        return componentInstance.save(book);
    }

    public void remove(String bookIdx, SearchApi searchApi){
        ComponentInterface componentInterface = getComponentInstance(searchApi);
        componentInterface.remove(bookIdx);
    }
}

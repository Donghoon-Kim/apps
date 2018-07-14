package com.kb.bookapp.repository.custom;

import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.business.component.UserApi.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBookRepository {
    Page<UserBook> findBySearchCondition(SearchCondition searchCondition, Pageable pageable);
}

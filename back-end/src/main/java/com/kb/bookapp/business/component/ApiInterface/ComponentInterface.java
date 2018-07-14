package com.kb.bookapp.business.component.ApiInterface;

public interface ComponentInterface {
    SearchBookResult requestSearch(SearchCondition searchCondition);
    BookInterface save(BookInterface book);
    void remove(String bookIdx);
}

package com.kb.bookapp.business.component.UserApi;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.component.ApiInterface.ComponentInterface;
import com.kb.bookapp.business.component.ApiInterface.SearchBookResult;
import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class UserBookApiComponent implements ComponentInterface {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public SearchBookResult requestSearch(SearchCondition searchCondition) {
        Pageable pageable = PageRequest.of(searchCondition.getPage()-1, searchCondition.getSize());
        Page<UserBook> userBookPage = bookRepository.findBySearchCondition(searchCondition, pageable);
        boolean isEnd = userBookPage.isLast();
        int pageableCount = userBookPage.getTotalPages();


        SearchBookResult result = new SearchBookResult(
                userBookPage.getContent().toArray(new UserBook[userBookPage.getContent().size()]),
                new UserBookMeta(isEnd, pageableCount, pageableCount),
                searchCondition
        );

        return result;
    }

    @Override
    public BookInterface save(BookInterface book) {
        UserBook userBook = (UserBook) book;
        userBook.getBookPrimaryInfo().setRegiDatetime(LocalDateTime.now());
        userBook.setSales((int) Math.random());
        userBook.setAccuracy((int) Math.random());
        bookRepository.save(userBook);

        return userBook;
    }

    @Override
    public void remove(String bookIdx) {
        bookRepository.deleteById(Long.valueOf(bookIdx));
    }
}

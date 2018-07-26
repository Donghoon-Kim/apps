package com.kb.bookapp.business.component;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.component.ApiInterface.SearchBookResult;
import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.business.component.UserApi.UserBook;
import com.kb.bookapp.business.component.UserApi.UserBookApiComponent;
import com.kb.bookapp.business.domain.BookPrimaryInfo;
import com.kb.bookapp.repository.BookRepository;
import com.kb.bookapp.test.SpringTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserBookApiComponentTest extends SpringTest {
    @Autowired
    @InjectMocks
    private UserBookApiComponent userBookApiComponent;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private SearchCondition searchCondition;

    @Before
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRequestSearchSuccess() {
        // given
        final int RESULT_SIZE = 10;
        Page<UserBook> userBookPage = new PageImpl<>(
                IntStream.range(1, 1 + RESULT_SIZE)
                        .mapToObj(num -> new UserBook())
                        .collect(toList())
        );
        when(searchCondition.getPage()).thenReturn(1);
        when(searchCondition.getSize()).thenReturn(10);
        when(bookRepository.findBySearchCondition(any(SearchCondition.class), any(Pageable.class)))
                .thenReturn(userBookPage);

        // when
        SearchBookResult searchBookResult = userBookApiComponent.requestSearch(searchCondition);

        // then
        assertThat(searchBookResult.getDocuments().length, is(RESULT_SIZE));
    }

    @Test
    public void testSaveSuccess() {
        // given
        final int SAVED_BOOK_IDX = 50;

        BookPrimaryInfo bookPrimaryInfo = new BookPrimaryInfo();
        bookPrimaryInfo.setTitle("test_title");
        bookPrimaryInfo.setPublisher("test_publisher");
        bookPrimaryInfo.setPrice(BigDecimal.TEN);
        bookPrimaryInfo.setIsbn("test_isbn");
        bookPrimaryInfo.setContents("test_contents");
        bookPrimaryInfo.setAuthor("test_authors");
        bookPrimaryInfo.setRegiDatetime(LocalDateTime.MIN);
        UserBook userBook = new UserBook();
        userBook.setSales(10);
        userBook.setOverview("test_overview");
        userBook.setAccuracy(1_000);
        userBook.setBookPrimaryInfo(bookPrimaryInfo);

        BookPrimaryInfo savedBookPrimaryInfo = new BookPrimaryInfo();
        savedBookPrimaryInfo.setTitle("test_title");
        savedBookPrimaryInfo.setPublisher("test_publisher");
        savedBookPrimaryInfo.setPrice(BigDecimal.TEN);
        savedBookPrimaryInfo.setIsbn("test_isbn");
        savedBookPrimaryInfo.setContents("test_contents");
        savedBookPrimaryInfo.setAuthor("test_authors");
        savedBookPrimaryInfo.setRegiDatetime(LocalDateTime.MIN);

        UserBook savedUserBook = new UserBook();
        savedUserBook.setSales(10);
        savedUserBook.setOverview("test_overview");
        savedUserBook.setAccuracy(1_000);
        savedUserBook.setBookPrimaryInfo(bookPrimaryInfo);
        savedUserBook.setUserBookIdx(SAVED_BOOK_IDX);

        when(bookRepository.save(any(UserBook.class))).thenReturn(savedUserBook);

        // when
        BookInterface result = userBookApiComponent.save(userBook);

        //then
        assertThat(result.getBarcode(), is(SAVED_BOOK_IDX + ""));
        assertThat(result.getDatetime(), is(notNullValue()));
    }
}

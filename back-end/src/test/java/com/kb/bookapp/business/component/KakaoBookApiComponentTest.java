package com.kb.bookapp.business.component;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.component.ApiInterface.SearchBookResult;
import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import com.kb.bookapp.business.component.kakaoApi.Book;
import com.kb.bookapp.business.component.kakaoApi.KakaoBookApiComponent;
import com.kb.bookapp.test.SpringTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class KakaoBookApiComponentTest extends SpringTest {
    @Autowired
    private KakaoBookApiComponent kakaoBookApiComponent;

    @Mock
    private SearchCondition searchCondition;

    @Before
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequestSearchFailBecauseQueryParameterEmpty() {
        // given
        when(searchCondition.getQuery()).thenReturn("");

        // when
        kakaoBookApiComponent.requestSearch(searchCondition);
    }

    @Test
    public void testRequestSearchSuccess() {
        // given
        when(searchCondition.getQuery()).thenReturn("대한");
        when(searchCondition.getPage()).thenReturn(1);
        when(searchCondition.getSize()).thenReturn(10);

        // when
        SearchBookResult searchBookResult = kakaoBookApiComponent.requestSearch(searchCondition);

        // then
        assertThat(searchBookResult.getDocuments().length, is(10));
    }

    @Test(expected = RuntimeException.class)
    public void testSaveFailBecauseNotSupportedMethod() {
        // when
        BookInterface saved = kakaoBookApiComponent.save(new Book());
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveFailBecauseNotSupportedMethod() {
        // when
        kakaoBookApiComponent.remove("1");
    }


}

package com.kb.bookapp.business.component.kakaoApi;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.component.ApiInterface.ComponentInterface;
import com.kb.bookapp.business.component.ApiInterface.SearchBookResult;
import com.kb.bookapp.business.component.ApiInterface.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class KakaoBookApiComponent implements ComponentInterface {
    private static final String APP_KEY = "KakaoAK 53b1d02cb461aec7a69a97521958e893";
    private static final String HOST = "dapi.kakao.com";
    private static final String[] OPTIONAL_PARAMS = new String[]{"sort", "target", "category", "page", "size"};

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders getKakaoRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", APP_KEY);
        return headers;
    }


    public SearchBookResult requestSearch(SearchCondition searchCondition) {
        if (StringUtils.isEmpty(searchCondition.getQuery())) {
            throw new IllegalArgumentException("query 전달인자를 포함해야함.");
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance().scheme("https").host(HOST)
                .path("/v2/search/book")
                .queryParam("query", searchCondition.getQuery())
                .queryParam("page", searchCondition.getPage())
                .queryParam("size", searchCondition.getSize())
                .queryParam("target", searchCondition.getTarget())
                .queryParam("sort", "???");

        URI uri = uriBuilder.build().encode().toUri();
        HttpEntity<Object> entity = new HttpEntity<>(null, getKakaoRequestHeader());
        ResponseEntity<SearchBookResponseEntity> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, SearchBookResponseEntity.class);
        SearchBookResponseEntity body = responseEntity.getBody();

        return new SearchBookResult(body.getDocuments(), body.getMeta(), searchCondition);
    }

    @Override
    public BookInterface save(BookInterface book) {
        throw new RuntimeException("이 API는 해당 기능을 지원하지 않습니다.");
    }

    @Override
    public void remove(String bookIdx) {
        throw new RuntimeException("이 API는 해당 기능을 지원하지 않습니다.");
    }
}

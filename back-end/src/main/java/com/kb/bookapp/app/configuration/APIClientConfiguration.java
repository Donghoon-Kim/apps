package com.kb.bookapp.app.configuration;

import com.kb.bookapp.business.component.ApiInterface.ComponentInterface;
import com.kb.bookapp.business.domain.SearchApi;
import com.kb.bookapp.repository.SearchApiRepository;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static com.kb.bookapp.app.constant.Constants.*;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

@Configuration
public class APIClientConfiguration {
    @Autowired
    private ComponentInterface[] componentInterfaces;

    @Autowired
    private SearchApiRepository searchApiRepository;

    @Bean
    public LinkedHashMap<Long, ComponentInterface> getSearchApis() {
        LinkedHashMap<Long, ComponentInterface> apiMap = new LinkedHashMap<>();
        List<SearchApi> apis = searchApiRepository.findAll().stream()
                .sorted(comparingInt(SearchApi::getOrders))
                .collect(toList());

        for (SearchApi api : apis) {
            ComponentInterface componentInterface = Arrays.stream(componentInterfaces)
                    .filter(component -> component.getClass().getName().equals(api.getFullClassName()))
                    .findFirst().orElse(null);

            if (componentInterface != null) {
                apiMap.put(api.getSearchApiIdx(), componentInterface);
            }
        }

        return apiMap;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(HTTP_CLIENT_MAX_CONN_TOTAL)
                .setMaxConnPerRoute(HTTP_CLIENT_MAX_CONN_PER_ROUTE)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(HTTP_CLIENT_FACTORY_READ_TIMEOUT);
        factory.setConnectTimeout(HTTP_CLINET_FACTORY_CONN_TIMEOUT);
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }
}

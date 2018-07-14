package com.kb.bookapp.business.service;

import com.kb.bookapp.business.domain.SearchApi;
import com.kb.bookapp.repository.SearchApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchApiService {
    @Autowired
    private SearchApiRepository searchApiRepository;

    public List<SearchApi> findAll() {
        return searchApiRepository.findByOrderByOrders();
    }
}

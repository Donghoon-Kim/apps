package com.kb.bookapp.repository;

import com.kb.bookapp.business.domain.SearchApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchApiRepository extends JpaRepository<SearchApi, Long> {
    List<SearchApi> findByOrderByOrders();
}

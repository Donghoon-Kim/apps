package com.kb.bookapp.repository;

import com.kb.bookapp.business.domain.QueryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryHistoryRepository extends JpaRepository<QueryHistory, Long> {
    List<QueryHistory> findByMemberUsernameOrderByDatetimeDesc(String username);
}

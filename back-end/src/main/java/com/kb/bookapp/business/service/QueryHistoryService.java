package com.kb.bookapp.business.service;

import com.kb.bookapp.business.domain.Member;
import com.kb.bookapp.business.domain.QueryHistory;
import com.kb.bookapp.repository.MemberRepository;
import com.kb.bookapp.repository.QueryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueryHistoryService {
    @Autowired
    private QueryHistoryRepository queryHistoryRepository;
    @Autowired
    private MemberRepository memberRepository;

    public QueryHistory save(QueryHistory queryHistory, String username){
        if(StringUtils.isEmpty(queryHistory.getQuery())){
            throw new IllegalArgumentException("쿼리를 전송해주십시오");
        }

        Member member = memberRepository.findByUsername(username);
        queryHistory.setMember(member);
        queryHistory.setDatetime(LocalDateTime.now());
        queryHistoryRepository.save(queryHistory);

        return queryHistory;
    }

    public List<QueryHistory> findQueryHistory(String username){
        return queryHistoryRepository.findByMemberUsernameOrderByDatetimeDesc(username);
    }
}

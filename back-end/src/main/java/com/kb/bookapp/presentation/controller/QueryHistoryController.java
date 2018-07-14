package com.kb.bookapp.presentation.controller;

import com.kb.bookapp.business.domain.QueryHistory;
import com.kb.bookapp.business.service.QueryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queryHistories")
public class QueryHistoryController {
    @Autowired
    private QueryHistoryService queryHistoryService;

    @PostMapping
    public ResponseEntity saveQueryHistory(@RequestBody QueryHistory queryHistory, Authentication authentication) {
        return new ResponseEntity(queryHistoryService.save(queryHistory, authentication.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getQueryHistory(Authentication authentication) {
        return new ResponseEntity(queryHistoryService.findQueryHistory(authentication.getName()), HttpStatus.OK);
    }
}

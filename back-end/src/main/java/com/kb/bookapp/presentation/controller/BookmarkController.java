package com.kb.bookapp.presentation.controller;

import com.kb.bookapp.business.domain.Bookmark;
import com.kb.bookapp.business.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;
    @PostMapping
    public ResponseEntity save(@RequestBody Bookmark bookmark, Authentication authentication){
        return new ResponseEntity(bookmarkService.save(bookmark, authentication.getName()), HttpStatus.CREATED );
    }
    @GetMapping
    public ResponseEntity getBookmark(Authentication authentication){
        return new ResponseEntity((bookmarkService.getBookmarkList(authentication.getName())), HttpStatus.OK);
    }
}

package com.kb.bookapp.presentation.controller;

import com.kb.bookapp.business.component.UserApi.UserBook;
import com.kb.bookapp.business.domain.SearchApi;
import com.kb.bookapp.business.service.BookService;
import com.kb.bookapp.business.service.SearchApiService;
import com.kb.bookapp.presentation.validator.UserBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/searchApis")
public class SearchApiController {
    @Autowired
    private SearchApiService searchApiService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserBookValidator userBookValidator;
    @InitBinder("userBook")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(userBookValidator);
    }

    @GetMapping
    public ResponseEntity findSearchApis(){
        return new ResponseEntity(searchApiService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{searchApiIdx}/books")
    public ResponseEntity search(@RequestParam HashMap<String, String> paramMap, @PathVariable("searchApiIdx") SearchApi searchApi) {
        return new ResponseEntity(bookService.search(searchApi, paramMap), HttpStatus.OK);
    }

    @PostMapping("/{searchApiIdx}/books")
    public ResponseEntity save(@RequestBody @Valid UserBook userBook, @PathVariable("searchApiIdx") SearchApi searchApi) {
        userBook.setSearchApi(searchApi);
        return new ResponseEntity(bookService.save(userBook, searchApi), HttpStatus.CREATED);
    }

    @DeleteMapping("/{searchApiIdx}/books/{bookIdx}")
    public ResponseEntity remove(@PathVariable("bookIdx") String bookIdx, @PathVariable("searchApiIdx") SearchApi searchApi) {
        bookService.remove(bookIdx, searchApi);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}

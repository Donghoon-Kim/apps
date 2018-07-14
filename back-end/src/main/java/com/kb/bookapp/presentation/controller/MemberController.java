package com.kb.bookapp.presentation.controller;

import com.kb.bookapp.business.domain.Member;
import com.kb.bookapp.business.service.MemberService;
import com.kb.bookapp.presentation.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberValidator memberValidator;

    @InitBinder("member")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(memberValidator);
    }

    @PostMapping
    public ResponseEntity createMember(@RequestBody @Valid Member member) {
        memberService.createMember(member);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity test() {
        return null;
    }


    @PostMapping("/logout")
    public ResponseEntity logout() {
        return null;
    }

}

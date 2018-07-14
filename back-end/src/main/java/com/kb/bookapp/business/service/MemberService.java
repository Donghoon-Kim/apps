package com.kb.bookapp.business.service;

import com.kb.bookapp.business.domain.Member;
import com.kb.bookapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static java.util.Collections.emptyList;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member createMember(Member member) {
        Member existMember = memberRepository.findByUsername(member.getUsername());
        if(existMember != null){
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }

        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + "은(는) 존재하지 않는 아이디입니다.");
        }
        return new User(member.getUsername(), member.getPassword(), emptyList());
    }
}

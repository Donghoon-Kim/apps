package com.kb.bookapp.business.service;

import com.kb.bookapp.business.domain.Bookmark;
import com.kb.bookapp.business.domain.Member;
import com.kb.bookapp.repository.BookRepository;
import com.kb.bookapp.repository.BookmarkRepository;
import com.kb.bookapp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<Bookmark> getBookmarkList(String username){
        Member member = memberRepository.findByUsername(username);
        return bookmarkRepository.findByMemberMemberIdxOrderByBookmarkRegiDatetimeDesc(member.getMemberIdx());
    }

    public Bookmark save(Bookmark bookmark, String username){
        Member member = memberRepository.findByUsername(username);
        bookmark.setBookmarkRegiDatetime(LocalDateTime.now());
        bookmark.setMember(member);

        bookmarkRepository.save(bookmark);

        return bookmark;
    }
}

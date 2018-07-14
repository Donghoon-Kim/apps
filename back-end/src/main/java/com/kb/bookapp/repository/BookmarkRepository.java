package com.kb.bookapp.repository;

import com.kb.bookapp.business.component.UserApi.UserBook;
import com.kb.bookapp.business.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByMemberMemberIdxOrderByBookmarkRegiDatetimeDesc(long memberIdx);
}

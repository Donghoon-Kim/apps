package com.kb.bookapp.business.domain;

import com.kb.bookapp.business.component.UserApi.UserBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookmarkIdx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Embedded
    private BookPrimaryInfo bookPrimaryInfo;

    private LocalDateTime bookmarkRegiDatetime;
}

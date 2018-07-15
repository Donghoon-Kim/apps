package com.kb.bookapp.business.component.UserApi;

import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import com.kb.bookapp.business.domain.BookPrimaryInfo;
import com.kb.bookapp.business.domain.SearchApi;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
public class UserBook implements BookInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userBookIdx;

    @Embedded
    private BookPrimaryInfo bookPrimaryInfo;

    private String keyword;
    private String overview;
    private int accuracy;
    private int sales;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_api_idx")
    private SearchApi searchApi;

    @Override
    public String getBarcode() {
        return String.valueOf(userBookIdx);
    }

    @Override
    public String getEbookBarcode() {
        return "";
    }

    @Override
    public BigDecimal getSalePrice() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getSaleYn() {
        return "";
    }

    @Override
    public String getStatus() {
        return "";
    }

    @Override
    public String getThumbnail() {
        return "";
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public String[] getAuthors() {
        return new String[]{bookPrimaryInfo.getAuthor()};
    }

    @Override
    public String getContents() {
        return bookPrimaryInfo.getContents();
    }

    @Override
    public LocalDateTime getDatetime() {
        return bookPrimaryInfo.getRegiDatetime();
    }

    @Override
    public String getIsbn() {
        return bookPrimaryInfo.getIsbn();
    }

    @Override
    public BigDecimal getPrice() {
        return bookPrimaryInfo.getPrice();
    }

    @Override
    public String getPublisher() {
        return bookPrimaryInfo.getPublisher();
    }

    @Override
    public String getTitle() {
        return bookPrimaryInfo.getTitle();
    }

    @Override
    public String[] getTranslators() {
        return new String[0];
    }

    public BookPrimaryInfo getBookPrimaryInfo() {
        return bookPrimaryInfo;
    }
}

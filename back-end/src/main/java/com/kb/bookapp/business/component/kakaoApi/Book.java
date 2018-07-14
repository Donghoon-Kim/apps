package com.kb.bookapp.business.component.kakaoApi;


import com.kb.bookapp.business.component.ApiInterface.BookInterface;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
public class Book implements BookInterface {
    private String barcode;
    private String ebook_barcode;
    private String sale_price;
    private String salePrice;
    private String saleprice;
    private String sale_yn;
    private String status;
    private String thumbnail;
    private String url;
    private String[] authors;
    private String contents;
    private String datetime;
    private String isbn;
    private String price;
    private String publisher;
    private String title;
    private String[] translators;

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public String getEbookBarcode() {
        return ebook_barcode;
    }

    @Override
    public BigDecimal getSalePrice() {
        return BigDecimal.valueOf(Long.valueOf(sale_price));
    }

    @Override
    public String getSaleYn() {
        return sale_yn;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String[] getAuthors() {
        return authors;
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public LocalDateTime getDatetime() {
        return KakaoDateTimeUtil.convertStringToLocalDateTime(datetime);
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(Long.valueOf(price));
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String[] getTranslators() {
        return translators;
    }
}
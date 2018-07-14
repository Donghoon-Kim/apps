package com.kb.bookapp.business.component.ApiInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BookInterface {
    String getBarcode();

    String getEbookBarcode();

    BigDecimal getSalePrice();

    String getSaleYn();

    String getStatus();

    String getThumbnail();

    String getUrl();

    String[] getAuthors();

    String getContents();

    LocalDateTime getDatetime();

    String getIsbn();

    BigDecimal getPrice();

    String getPublisher();

    String getTitle();

    String[] getTranslators();
}

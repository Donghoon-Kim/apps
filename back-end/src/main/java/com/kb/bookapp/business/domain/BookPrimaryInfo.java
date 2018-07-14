package com.kb.bookapp.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookPrimaryInfo {
    private String title;
    private String contents;
    private BigDecimal price;
    private String author;
    private String publisher;
    private LocalDateTime regiDatetime;
    private String isbn;

}

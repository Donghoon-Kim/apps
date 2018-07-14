package com.kb.bookapp.business.component.kakaoApi;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchBookResponseEntity {
    private Book[] documents;
    private Meta meta;
}
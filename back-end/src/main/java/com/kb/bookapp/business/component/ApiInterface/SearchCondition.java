package com.kb.bookapp.business.component.ApiInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SearchCondition {
    private int page;
    private int size;
    private long apiIdx;
    private String query;
    private String target;
    private String sort;
    private long categoryIdx;
}

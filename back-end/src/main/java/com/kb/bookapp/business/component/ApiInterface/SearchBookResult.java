package com.kb.bookapp.business.component.ApiInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SearchBookResult {
    private BookInterface[] documents;
    private MetaInterface meta;
    private SearchCondition searchCondition;


    public <D extends BookInterface,M extends MetaInterface> SearchBookResult(D[] documents, M meta, SearchCondition searchCondition){
        this.documents = documents;
        this.meta = meta;
        this.searchCondition = searchCondition;
    }
}
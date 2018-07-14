package com.kb.bookapp.business.component.UserApi;

import com.kb.bookapp.business.component.ApiInterface.MetaInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBookMeta implements MetaInterface {
    private boolean isEnd;
    private int pageableCount;
    private int totalCount;
}

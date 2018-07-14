package com.kb.bookapp.business.component.kakaoApi;


import com.kb.bookapp.business.component.ApiInterface.MetaInterface;
import lombok.Setter;

@Setter
public class Meta implements MetaInterface {
    private boolean is_end;
    private int pageable_count;
    private int total_count;

    @Override
    public boolean isEnd() {
        return is_end;
    }

    @Override
    public int getPageableCount() {
        return pageable_count;
    }

    @Override
    public int getTotalCount() {
        return total_count;
    }
}
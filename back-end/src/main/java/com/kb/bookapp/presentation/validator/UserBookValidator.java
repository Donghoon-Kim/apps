package com.kb.bookapp.presentation.validator;

import com.kb.bookapp.business.component.UserApi.UserBook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserBookValidator implements Validator {
    @Override
    public void validate(Object target, Errors errors) {
        UserBook userBook = (UserBook) target;

        if(StringUtils.isEmpty(userBook.getTitle())){
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }

        if(StringUtils.isEmpty(userBook.getContents())){
            throw new IllegalArgumentException("내용을 입력해주세요");
        }

        if(StringUtils.isEmpty(userBook.getPrice())){
            throw new IllegalArgumentException("가격을 입력해주세요");
        }

        if(StringUtils.isEmpty(userBook.getAuthors())){
            throw new IllegalArgumentException("저자를 입력해주세요");
        }

        if(StringUtils.isEmpty(userBook.getPublisher())){
            throw new IllegalArgumentException("출판사를 입력해주세요");
        }

        if(StringUtils.isEmpty(userBook.getIsbn())){
            throw new IllegalArgumentException("Isbn을 입력해주세요");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserBook.class.isAssignableFrom(aClass);
    }
}

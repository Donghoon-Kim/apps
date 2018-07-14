package com.kb.bookapp.presentation.validator;

import com.kb.bookapp.business.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {
    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        if(StringUtils.isEmpty(member.getUsername())){
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }

        if(StringUtils.isEmpty(member.getPassword())){
            throw new IllegalArgumentException("패스워드를 입력해주세요");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Member.class.isAssignableFrom(aClass);
    }
}

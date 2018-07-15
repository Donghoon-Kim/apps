package com.kb.bookapp.business.component.EncryptComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RsaTextBCryptPasswordEncoder extends BCryptPasswordEncoder {

    @Autowired
    private RsaTextDecoder rsaTextDecoder;

    @Override
    public String encode(CharSequence rawPassword) {
        if(rawPassword.equals("userNotFoundPassword")){
            return super.encode(rawPassword);
        }
        return super.encode(rsaTextDecoder.decode(rawPassword));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rsaTextDecoder.decode(rawPassword), encodedPassword);
    }
}

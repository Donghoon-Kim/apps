package com.kb.bookapp.app.configuration;

import com.kb.bookapp.business.component.RsaTextDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.*;

@Configuration
public class EncryptConfiguration {
    @Bean
    public RsaTextDecoder getRsaTextDecoder(){
        return new RsaTextDecoder();
    }
}
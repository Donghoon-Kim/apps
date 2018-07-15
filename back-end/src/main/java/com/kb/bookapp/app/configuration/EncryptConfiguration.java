package com.kb.bookapp.app.configuration;

import com.kb.bookapp.business.component.EncryptComponent.RsaTextDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptConfiguration {
    @Bean
    public RsaTextDecoder getRsaTextDecoder(){
        return new RsaTextDecoder();
    }
}
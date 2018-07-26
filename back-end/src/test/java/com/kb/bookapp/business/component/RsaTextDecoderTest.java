package com.kb.bookapp.business.component;

import com.kb.bookapp.business.component.EncryptComponent.RsaTextDecoder;
import com.kb.bookapp.test.SpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RsaTextDecoderTest extends SpringTest {

    @Autowired
    private RsaTextDecoder rsaTextDecoder;

    @Test
    public void testDecodeSuccess(){
        // given
        final String ORIGIN_TEXT = "1234";
        final String ENCODED_TEXT_1234_1 = "QaSYezWmJ3yXgNsKVzlmDf/6EY6e0pSANUQ/u2rtRuRBPAPb3ScZnxjqQVBCwt8vIzRNN9TB1bcVloMQsAWrw3Fx4GxeswNdTqyc8IhyZYkYFsmTmkEeA9KhwnPQnq7m4zstd2Oj8aSgQQ8ptl8XuQkAUHeOSoG/bqUZf54kP8k=";
        final String ENCODED_TEXT_1234_2 = "Drt8GKkCyRDtzeFfhJ2cptUq9KQShAap5HVoSsq4cQs1QsyDI13u0fVbm9U1/Xh+3mrUDZUlI25HMKOETgFteuY65f1s94Vim7hvyYXIdwYlVckeSN+w2YJHPFhQfqIdcKpPtfHy8j74UWA5n0qPWphokjVLRSPkpAjMfs9htns=";

        // when
        String result1 = rsaTextDecoder.decode(ENCODED_TEXT_1234_1);
        String result2 = rsaTextDecoder.decode(ENCODED_TEXT_1234_2);

        // then
        assertThat(result1, is(ORIGIN_TEXT));
        assertThat(result2, is(ORIGIN_TEXT));
    }

    @Test(expected = RuntimeException.class)
    public void testDecodeFailBecauseIllegalText(){
        // given
        final String ENCODED_TEXT_ILLEGAL = "암호화된 텍스트";

        // when
        String result = rsaTextDecoder.decode(ENCODED_TEXT_ILLEGAL);
    }
}

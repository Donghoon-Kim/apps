package com.kb.bookapp.business.component.EncryptComponent;

import org.springframework.stereotype.Component;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

@Component
public class RsaTextDecoder {
    private final static String PKCS1_PREFIX_STRING = "-----BEGIN RSA PRIVATE KEY-----" + System.getProperty("line.separator");
    private final static String PKCS1_SUFFIX_STRING = "-----END RSA PRIVATE KEY-----";
    private final static String PKCS1_DELIM = System.getProperty("line.separator");
    private final static String EMPTY_STRING = "";
    private final static String KEY_FILE = "/server.key.pem";
    private final static short KEY_SIZE = 1024;

    private byte[] readAllBytes(InputStream in) {
        byte[] result = new byte[]{};
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[KEY_SIZE];
            for (int read = 0; read != -1; read = in.read(buf)) {
                baos.write(buf, 0, read);
            }
            result = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("인증서 파일 읽는 도중 에러 발생");
        }

        return result;
    }

    public String decode(CharSequence text) {
        return decode(text.toString());
    }

    public String decode(String text) {
        String resultString = null;
        URL url = getClass().getResource(KEY_FILE);
        try (InputStream in = url.openStream()) {
            String pem = new String(readAllBytes(in));
            String key = pem.replace(PKCS1_PREFIX_STRING, EMPTY_STRING)
                    .replace(PKCS1_SUFFIX_STRING, EMPTY_STRING)
                    .replace(PKCS1_DELIM, EMPTY_STRING)
                    .trim();

            byte[] encoded = Base64.getMimeDecoder().decode(key);
            DerInputStream derInputStream = new DerInputStream(encoded);
            DerValue[] seq = derInputStream.getSequence(0);

            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp,
                    privateExp, prime1, prime2, exp1, exp2, crtCoef);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] hex = Base64.getDecoder().decode(text);
            resultString = new String(cipher.doFinal(hex));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException("복호화 도중 에러 발생");
        }

        return resultString;
    }
}

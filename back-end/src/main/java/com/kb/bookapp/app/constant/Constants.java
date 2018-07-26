package com.kb.bookapp.app.constant;

public class Constants {
    public static final String TOKEN_SIGN_STRING = "sign_key";
    public static final String TOKEN_HEADER = "authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final int TOKEN_EXPIRE_MILLISECOND = 86_400_000;

    public static final int HTTP_CLIENT_MAX_CONN_TOTAL = 100;
    public static final int HTTP_CLIENT_MAX_CONN_PER_ROUTE = 5;
    public static final int HTTP_CLIENT_FACTORY_READ_TIMEOUT = 5_000;
    public static final int HTTP_CLINET_FACTORY_CONN_TIMEOUT = 3_000;
}

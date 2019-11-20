package com.example.demo.security;

import com.example.demo.SpringApplicationContext;

public class SecutiryConstants {

    public static final long EXPIRATION_TIME= 96400000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING= "Authorization";
    public static final String SIGN_UP_URL  = "/user";
    public static final String VERIFICATION_EMAIL_URL  = "/user/ email-verification";




    public static String getTokenSecret(){
        AppProperties appProperties= (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}

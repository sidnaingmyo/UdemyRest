package com.example.demo.tokentest;

import com.example.demo.security.SecutiryConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Token {
    public static boolean hastokenExipred(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(SecutiryConstants.getTokenSecret())
                .parseClaimsJws(token).getBody();

        Date tokenExpirationDate=claims.getExpiration();
        Date todayDate=new Date();

        return tokenExpirationDate.before(todayDate);
    }

    public static String generateEmailVerificationToken(String userId){
        String token=Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis()+ SecutiryConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.ES512,SecutiryConstants.getTokenSecret())
                .compact();
        return token;
    }
}

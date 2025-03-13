package com.xiao.utils;

import com.xiao.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Slf4j
public class JWTUtil {

    public static final String JWT_ID = UUID.randomUUID().toString();
    public static final String JWT_SECRET = "jiamimiwen";
    public static final int EXPIRE_TIME = 60 * 60 * 1000;

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static String createJWT(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        long nowTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowTime);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(JWT_ID)
                .setIssuedAt(issuedAt)
                .signWith(signatureAlgorithm, key);
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        SecretKey key = generalKey();
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }
}
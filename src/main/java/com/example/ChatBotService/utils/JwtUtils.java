package com.example.ChatBotService.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;

@Service
public class JwtUtils {
    private static final String SECRET_KEY="4c7e6b2d3f8e2c9f1b5c0a7e2f0c6d3a0f7a1d8e5b9c4e2f3a7b1c2d9e5f4a3b";

    public String extractUserName(String token)
    {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("username", String.class); // Extract userId
        } catch (Exception e) {
            throw new RuntimeException("Could not extract userId from JWT");
        }
    }
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

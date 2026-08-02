package com.thanmailabs.taskflow.service.impl;

import com.thanmailabs.taskflow.entity.User;
import com.thanmailabs.taskflow.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@ConfigurationProperties(prefix = "jwt")
public class JwtServiceImpl implements JwtService {
    private String secret;
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateAccessToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String getUserNameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    @Override
    public boolean isValidAccessToken(String token) {
        return false;
    }
}

package com.api.secureauthapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "TuClaveSecretaSuperLargaParaFirmarElJWT"; // Usa una clave de al menos 256 bits
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), Jwts.SIG.HS256) // Nueva forma de firmar tokens en JJWT 0.12+
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // Nueva forma de verificar en JJWT 0.12+
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
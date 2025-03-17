package com.zestindia.t2.security.jwt;

import ch.qos.logback.core.net.server.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JwtTokenService {
    private static final String SECRET = "ZHxTQgs/igL2PulV9fcpINQ5lS24hcsfzXyS2ugbpO9RiALHdGThOontzmO9RMaBUFgP0t/qDHZCTzbnSI1lpw==";
    private static final long VALIDITY = TimeUnit.HOURS.toMillis(1);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("issuedAt", Date.from(Instant.now()));
        claims.put("expiresAt", Date.from(Instant.now().plusMillis(VALIDITY)));

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isValid(String token) {
        try {
            Claims claims=getClaims(token);
            if(claims!=null)
            return claims.getExpiration().after(Date.from(Instant.now()));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private SecretKey getSecretKey() {
        byte[] key = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }
}

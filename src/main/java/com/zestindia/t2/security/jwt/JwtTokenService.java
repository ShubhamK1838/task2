package com.zestindia.t2.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtTokenService {
    private static final String SECRET = "ZHxTQgs/igL2PulV9fcpINQ5lS24hcsfzXyS2ugbpO9RiALHdGThOontzmO9RMaBUFgP0t/qDHZCTzbnSI1lpw==";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(60);


    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("start", LocalTime.now().plusMinutes(30).toString());
        claims.put("end", LocalTime.now().plusMinutes(30).toString());

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
        return getClaims(token).getExpiration().after(Date.from(Instant.now()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] key = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }

}

package com.ideavest.server.Security;

import com.ideavest.server.models.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "a4fcc5caa6ac303eb4dae17c79405a6430953b8b56a7e00db6ab3848c4fa214722d206a276295c113cc2593b8d967f8dcb6bc9add815078d960abfb445a889a1b19afefad8197222abe840dd3259f96f2062306e3c78147b776b86512b83d6d6b395a23f7755e9e3a2d35c1209db985f9e7a321bab18edb30c84b3703d5c5b0940ed29c78761bbbe30b94861e74b250e361ab4e7c0b56db7b9a747d88593e28106ed55a3db6385bbcbf681ab68c1c4bb0bc11e0be7c02ede8561ddb85c551f41544c5e69a3bc7efdbad76aa78c39a28f2754d9750a5de9da4e2a9652286fd21688996bac4f11f58a6512484906a87ae66c4ab7e7fec1e633ac398473b5da73bd"; // Change this in production
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 🔹 Generate JWT with role
    public String generateToken(String email, UserRole role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Extract Username
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 🔹 Extract Role
    public String extractRole(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().get("role", String.class);
    }

    // 🔹 Validate Token
    public boolean validateToken(String token, String email) {
        return extractUsername(token).equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}

package org.serratec.backend.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final Long jwtExpirationMiliseg;

    public JwtUtil(
        @Value("${auth.jwt-secret}") String jwtSecret,
        @Value("${auth.jwt-expiration-miliseg}") Long jwtExpirationMiliseg
    ) {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtExpirationMiliseg = jwtExpirationMiliseg;
    }

    // Apenas username
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMiliseg))
            .signWith(secretKey)
            .compact();
    }

    // Incluindo roles no token
    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .claim("roles", roles) // ADICIONANDO ROLES AO TOKEN
            .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMiliseg))
            .signWith(secretKey)
            .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            return username != null && expirationDate != null && new Date().before(expirationDate);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    // Extrair roles do token
    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Claims claims = getClaims(token);
        return claims.get("roles", List.class);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}
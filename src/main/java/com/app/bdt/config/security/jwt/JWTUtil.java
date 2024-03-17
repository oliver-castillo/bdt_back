package com.app.bdt.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {

  private static final String SECRET = "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gR54548521698";
  private static final long EXPIRATION_TIME = 864_000_000; // 10 days

  public static String generateToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
  }

  public static String extractUsername(String token) {
    return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }
}

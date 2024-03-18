package com.app.bdt.config.security.jwt;

import com.app.bdt.config.security.dto.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
  private final static Logger logger = LoggerFactory.getLogger(JWTProvider.class);

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private int expiration;

  public String generateToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    return Jwts.builder().setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("token mal formado");
    } catch (UnsupportedJwtException e) {
      logger.error("token no soportado");
    } catch (ExpiredJwtException e) {
      logger.error("token expirado");
    } catch (IllegalArgumentException e) {
      logger.error("token vacío");
    } catch (SignatureException e) {
      logger.error("fail en la firma");
    }
    return false;
  }
}

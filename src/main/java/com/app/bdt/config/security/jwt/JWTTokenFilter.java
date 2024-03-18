package com.app.bdt.config.security.jwt;

import com.app.bdt.config.security.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(JWTTokenFilter.class);

  @Autowired
  private JWTProvider jwtProvider;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = getToken(req);
      if (token != null && jwtProvider.validateToken(token)) {
        String username = jwtProvider.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      log.error(String.format("Errores en doFilter: %s", e.getMessage()));
    }
    filterChain.doFilter(req, res);
  }

  private String getToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer"))
      return header.replace("Bearer ", "");
    return null;
  }
}
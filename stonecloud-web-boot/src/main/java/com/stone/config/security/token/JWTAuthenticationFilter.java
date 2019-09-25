package com.stone.config.security.token;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTH_TOKEN = "AUTH_TOKEN";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authToken = request.getHeader(AUTH_TOKEN);
    SecurityContextHolder.clearContext();
    if (StringUtils.hasText(authToken)) {
      SecurityContextHolder.getContext().setAuthentication(new JWTAuthenticationToken(authToken));
    }
    try {
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.error("", e);
    }
  }
}

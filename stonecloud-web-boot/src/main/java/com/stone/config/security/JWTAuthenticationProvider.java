package com.stone.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return new JWTAuthenticationToken("");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JWTAuthenticationToken.class.isAssignableFrom(authentication);
  }
}

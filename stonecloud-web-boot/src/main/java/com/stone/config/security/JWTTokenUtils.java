package com.stone.config.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTTokenUtils {

  public static final String JWT_SECRET = "123456";

  public static String generateToken(String userId, String userName) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("userName", userName);
    String jwtToken = Jwts.builder().addClaims(claims)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    return jwtToken;
  }

  public static JWTAuthenticationToken parseToken(String jwtToken) {
    Claims claims = null;
    try {
    claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken).getBody();
  } catch (ExpiredJwtException e) {
    log.error("AuthToken expire authToken: {} ", jwtToken);
  } catch (Exception e) {
    log.error("Auth failed, authToken : {}", jwtToken, e);
  }
    List<Object> roles = Arrays.asList(claims.get("roles"));
    List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
    if (roles != null) {
      roleList = AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));
    }
    JWTAuthenticationToken auth = new JWTAuthenticationToken(jwtToken,
        String.valueOf(claims.get("userId")), 
        String.valueOf(claims.get("userName")), 
        roleList);
    return auth;

  }


}

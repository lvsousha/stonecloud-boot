package com.stone.config.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      username = "admin";
      log.info("登录用户名===========" + username);
      //这里需要去数据库查询用户的账号密码来比对是否正确，以及账号是否过期等等
      String password = passwordEncoder.encode("123456");
      log.info("数据库密码是==============" + password);
      return new User(username, password,
              true,
              true,
              true,
              true,
              AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}

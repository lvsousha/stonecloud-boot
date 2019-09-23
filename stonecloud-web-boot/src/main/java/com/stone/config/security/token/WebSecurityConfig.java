package com.stone.config.security.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  @Override
  public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/auth/**", "/api1/**", "/video/jump/**", "/video/show/**",
//        "/testWebSocket/**", "/resources/static/**", "/accessMeeting/**", "/download*",
//        "/sign/jump/**", "/TAk5by24Yw.txt", "/video/saveVideo*", "/swagger-ui.html", "/webjars/**",
//        "/swagger-resources/**", "/v2/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin().loginPage("/authentication/require") // 指定没有认证时跳转到的认证url
        .loginProcessingUrl("/authentication/form") // 提交登录表单的url
        .and()
          .authorizeRequests()
          .antMatchers("/authentication/require").permitAll()
          .anyRequest().authenticated()
        .and().csrf().disable();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

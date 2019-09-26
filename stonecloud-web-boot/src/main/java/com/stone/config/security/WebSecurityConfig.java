package com.stone.config.security;

import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.stone.config.SwitchControl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Resource
  private JWTAuthenticationProvider jwtAuthenticationProvider;
  @Autowired
  private SwitchControl switchControl;
  @Override
  public void configure(WebSecurity web) throws Exception {
    if (switchControl.getSpringSecurity()) {
      web.ignoring().antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**");
    } else {
      web.ignoring().anyRequest();
    }
    
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().exceptionHandling().and()
    .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
    .authenticationProvider(jwtAuthenticationProvider).authorizeRequests()
    .anyRequest().authenticated();
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

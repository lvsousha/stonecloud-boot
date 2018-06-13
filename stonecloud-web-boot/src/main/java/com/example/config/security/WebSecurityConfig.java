package com.example.config.security;

import java.util.Arrays;

import javax.annotation.Resource;

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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * 银行职员角色
   */
  private static final String ROLE_BANK = "BANK";
  /**
   * 公证员角色
   */
  private static final String ROLE_NOTARY = "NOTARY";
  /**
   * 系统管理员角色
   */
  private static final String ROLE_SYSADMIN = "SYSADMIN";

  @Resource
  private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Resource
  private JWTAuthenticationProvider jwtAuthenticationProvider;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers( "/auth/**", "/api1/**", "/video/jump/**",
        "/video/show/**", "/testWebSocket/**", "/resources/static/**", "/accessMeeting/**", "/download*",
        "/sign/jump/**", "/TAk5by24Yw.txt", "/video/saveVideo*", "/swagger-ui.html", "/webjars/**",
        "/swagger-resources/**", "/v2/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
        .accessDeniedHandler(new JWTAccessDeniedHandler()).and()
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
        .authenticationProvider(jwtAuthenticationProvider).authorizeRequests()

        .antMatchers("/bank/**").hasAnyAuthority(ROLE_BANK).antMatchers("/notarybusiness/**")
        .hasAnyAuthority(ROLE_NOTARY).antMatchers("/user/**", "/authRoleRelation/**", "/dictionary/**", "/menu/**",
            "/role/**", "/userRoleRelation/**")
        .hasAnyAuthority(ROLE_SYSADMIN)

        .filterSecurityInterceptorOncePerRequest(true).anyRequest().authenticated().and().logout().logoutUrl("/logout")
        .addLogoutHandler(new JWTLogoutHandler()).logoutSuccessHandler(new JWTLogoutSuccessHandler());
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

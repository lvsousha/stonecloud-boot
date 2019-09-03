package com.stone.config.security;
//package com.example.config.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	private Logger log = LoggerFactory.getLogger(BrowerSecurityConfig.class);
//	
//	@Autowired
//	private CustomerUserDetailsService customerUserDetailService;
//	
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        log.info("configure(HttpSecurity http)");
//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
//        		.loginPage("/login")           // 设置登录页面
//        		.loginProcessingUrl("/user/login")  // 自定义的登录接口
//        		.and()
//                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/**").permitAll()     // 设置所有人都可以访问登录页面
//                .anyRequest()               // 任何请求,登录后可以访问
//                .authenticated()
//                .and()
//                .csrf().disable();          // 关闭csrf防护
//    }
//    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("configure(AuthenticationManagerBuilder auth)");
//    	auth
//        	.userDetailsService(customerUserDetailService)
//        	.passwordEncoder(new BCryptPasswordEncoder()); //user Details Service验证
//
//    }
//    
//    
//}

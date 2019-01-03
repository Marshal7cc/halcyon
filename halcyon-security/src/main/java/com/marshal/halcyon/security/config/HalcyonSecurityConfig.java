package com.marshal.halcyon.security.config;

import com.marshal.halcyon.security.handler.HalcyonAuthenticationAccessDeniedHandler;
import com.marshal.halcyon.security.handler.HalcyonAuthenticationFailureHandler;
import com.marshal.halcyon.security.handler.HalcyonAuthenticationSuccessHandler;
import com.marshal.halcyon.security.handler.HalcyonLogoutHandler;
import com.marshal.halcyon.security.properties.HalcyonSecurityProperties;
import com.marshal.halcyon.security.service.HalcyonUserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * @auth: Marshal
 * @date: 2018/11/28
 * @desc: spring security配置
 */
@Configuration
public class HalcyonSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HalcyonSecurityProperties halcyonSecurityProperties;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HalcyonUserDetailService halcyonUserDetailService(){
        return new HalcyonUserDetailService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticateSuccessHandler() {
        HalcyonAuthenticationSuccessHandler authenticateSuccessHandler = new HalcyonAuthenticationSuccessHandler();
        authenticateSuccessHandler.setSessionRegistry(sessionRegistry());
        return authenticateSuccessHandler;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        HalcyonAuthenticationFailureHandler authenticationFailureHandler = new HalcyonAuthenticationFailureHandler();
        return authenticationFailureHandler;
    }

    @Bean
    public HalcyonAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler(){
        return new HalcyonAuthenticationAccessDeniedHandler();
    }

    @Bean
    public LogoutHandler logoutHandler(){
        HalcyonLogoutHandler logoutHandler = new HalcyonLogoutHandler();
        logoutHandler.setSessionRegistry(sessionRegistry());
        return logoutHandler;
    }
    /**
     * 密码加密工具，注入即自动检测使用
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources = StringUtils.splitByWholeSeparatorPreserveAllTokens(halcyonSecurityProperties.getStaticResources(), ",");


        http.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler())//权限不足处理器

             .and()
                .formLogin()
                .loginPage(halcyonSecurityProperties.getLoginPage())
                .loginProcessingUrl(halcyonSecurityProperties.getLoginUrl())
                .successHandler(authenticateSuccessHandler())
                .failureHandler(authenticationFailureHandler())
             .and()
                .userDetailsService(halcyonUserDetailService())
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
             .and()
             .and()
                .logout()
                .addLogoutHandler(logoutHandler())
                .logoutUrl(halcyonSecurityProperties.getLogoutUrl())
                .logoutSuccessUrl(halcyonSecurityProperties.getLoginPage())
                .deleteCookies("JSESSIONID")
             .and()
                .authorizeRequests() // 授权配置
                .antMatchers(staticResources).permitAll() // 免认证静态资源路径
                .antMatchers(
                        halcyonSecurityProperties.getLoginPage(),// 登录路径
                        halcyonSecurityProperties.getLoginUrl()
                ).permitAll() // 配置免认证路径
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
             .and()//允许iframe
                .headers()
                .frameOptions().sameOrigin()
             .and()
                .csrf().disable();
    }
}

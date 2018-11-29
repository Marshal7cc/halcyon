package com.marshal.mcap.security.config;

import com.marshal.mcap.security.handler.McapAuthenticationAccessDeniedHandler;
import com.marshal.mcap.security.handler.McapAuthenticationFailureHandler;
import com.marshal.mcap.security.handler.McapAuthenticationSuccessHandler;
import com.marshal.mcap.security.handler.McapLogoutHandler;
import com.marshal.mcap.security.properties.McapSecurityProperties;
import com.marshal.mcap.security.service.McapUserDetailService;
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
public class McapSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    McapSecurityProperties mcapSecurityProperties;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public McapUserDetailService mcapUserDetailService(){
        return new McapUserDetailService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticateSuccessHandler() {
        McapAuthenticationSuccessHandler authenticateSuccessHandler = new McapAuthenticationSuccessHandler();
        authenticateSuccessHandler.setSessionRegistry(sessionRegistry());
        return authenticateSuccessHandler;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        McapAuthenticationFailureHandler authenticationFailureHandler = new McapAuthenticationFailureHandler();
        return authenticationFailureHandler;
    }

    @Bean
    public McapAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler(){
        return new McapAuthenticationAccessDeniedHandler();
    }

    @Bean
    public LogoutHandler logoutHandler(){
        McapLogoutHandler logoutHandler = new McapLogoutHandler();
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

        String[] staticResources = StringUtils.splitByWholeSeparatorPreserveAllTokens(mcapSecurityProperties.getStaticResources(), ",");


        http.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler())//权限不足处理器

             .and()
                .formLogin()
                .loginPage(mcapSecurityProperties.getLoginPage())
                .loginProcessingUrl(mcapSecurityProperties.getLoginUrl())
                .successHandler(authenticateSuccessHandler())
                .failureHandler(authenticationFailureHandler())
             .and()
                .userDetailsService(mcapUserDetailService())
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
             .and()
             .and()
                .logout()
                .addLogoutHandler(logoutHandler())
                .logoutUrl(mcapSecurityProperties.getLogoutUrl())
                .logoutSuccessUrl(mcapSecurityProperties.getLoginPage())
                .deleteCookies("JSESSIONID")
             .and()
                .authorizeRequests() // 授权配置
                .antMatchers(staticResources).permitAll() // 免认证静态资源路径
                .antMatchers(
                        mcapSecurityProperties.getLoginPage(),// 登录路径
                        mcapSecurityProperties.getLoginUrl()
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

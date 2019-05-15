package com.marshal.halcyon.security.config;

import com.marshal.halcyon.security.component.ResourceVoter;
import com.marshal.halcyon.security.handler.CustomAuthenticationAccessDeniedHandler;
import com.marshal.halcyon.security.handler.CustomAuthenticationFailureHandler;
import com.marshal.halcyon.security.handler.CustomAuthenticationSuccessHandler;
import com.marshal.halcyon.security.handler.CustomLogoutHandler;
import com.marshal.halcyon.security.properties.SecurityProperties;
import com.marshal.halcyon.security.service.CustomUserDetailService;
import com.marshal.halcyon.security.session.CustomExpiredSessionStrategy;
import com.marshal.halcyon.security.session.CustomInvalidSessionStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/28
 * @desc: spring security配置
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    private CustomExpiredSessionStrategy expiredSessionStrategy;

    @Autowired
    private CustomInvalidSessionStrategy invalidSessionStrategy;

    /**
     * 认证管理器,确定用户,角色及相应的权限
     *
     * @return
     */
    @Bean
    UnanimousBased accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        //webExpressionVoter必须指定，否则报错
        voters.add(new WebExpressionVoter());
        voters.add(new RoleVoter());
        voters.add(new ResourceVoter());
        UnanimousBased accessDecisionManager = new UnanimousBased(voters);
        return accessDecisionManager;
    }

    @Bean
    public RedirectStrategy redirectStrategy() {
        return new DefaultRedirectStrategy();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public CustomUserDetailService halcyonUserDetailService() {
        return new CustomUserDetailService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticateSuccessHandler() {
        CustomAuthenticationSuccessHandler authenticateSuccessHandler = new CustomAuthenticationSuccessHandler();
        return authenticateSuccessHandler;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        CustomAuthenticationFailureHandler authenticationFailureHandler = new CustomAuthenticationFailureHandler();
        return authenticationFailureHandler;
    }

    @Bean
    public CustomAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler() {
        return new CustomAuthenticationAccessDeniedHandler();
    }

    @Bean
    public LogoutHandler logoutHandler() {
        CustomLogoutHandler logoutHandler = new CustomLogoutHandler();
        return logoutHandler;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码加密工具，注入即自动检测使用
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getStaticResources(), ",");


        http.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler())//权限不足处理器

                .and()
                .formLogin()
                .loginPage(securityProperties.getLoginPage())
                .loginProcessingUrl(securityProperties.getLoginUrl())
                .successForwardUrl(securityProperties.getDefaultTargetUrl())
                .successHandler(authenticateSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .userDetailsService(halcyonUserDetailService())
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)//一个用户持有session数超过限制时，会顶掉之前登录
                .expiredSessionStrategy(expiredSessionStrategy)
                .sessionRegistry(sessionRegistry())
                .and()
                .and()
                .logout()
                .addLogoutHandler(logoutHandler())
                .logoutUrl(securityProperties.getLogoutUrl())
                .logoutSuccessUrl(securityProperties.getLoginPage())
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests() // 授权配置
                .accessDecisionManager(accessDecisionManager())//认证管理器,管理voters
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/ureport/**").permitAll()
                .antMatchers(staticResources).permitAll() // 免认证静态资源路径
                .antMatchers(
                        securityProperties.getLoginPage(),// 登录路径
                        securityProperties.getLoginUrl(),
                        "/oauth/token",
                        "/api/**"
                ).permitAll()
                // 配置免认证路径
                .anyRequest() // 所有请求
                .authenticated() // 都需要认证
                .and()//允许iframe
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .csrf().disable();
    }
}

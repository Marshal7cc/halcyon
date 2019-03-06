package com.marshal.halcyon.oauth2.config;

import com.marshal.halcyon.oauth2.component.CustomRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @auth: Marshal
 * @date: 2019/3/4
 * @desc: oauth2配置文件
 */
@Configuration
public class Oauth2Config {
    /**
     * 资源服务器配置类
     */
    @Configuration
    @EnableResourceServer
    public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            super.configure(resources);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers()
                    .antMatchers("/api/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/**")
                    .authenticated();
        }
    }

    /**
     * 认证服务器配置类
     */
    @Configuration
    @EnableAuthorizationServer
    public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        DataSource dataSource;
        @Autowired
        CustomRedisTokenStore redisTokenStore;

        @Bean
        public ClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            super.configure(security);
            //important!
            security.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetails());
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(redisTokenStore)
                    .authenticationManager(authenticationManager);
        }
    }
}

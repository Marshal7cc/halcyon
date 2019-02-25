package com.marshal.halcyon.security.config;

import com.marshal.halcyon.security.properties.CasSecurityProperties;
import com.marshal.halcyon.security.properties.HalcyonSecurityProperties;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * @auth: Marshal
 * @date: 2019/2/24
 * @desc:
 */
@Configuration
@EnableConfigurationProperties({HalcyonSecurityProperties.class, CasSecurityProperties.class})
public class CasSecurityConfig {

    @Autowired
    HalcyonSecurityProperties halcyonSecurityProperties;

    @Autowired
    CasSecurityProperties casSecurityProperties;

    @Autowired
    AuthenticationManager authenticationManager;


    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(halcyonSecurityProperties.getHostUrl() + halcyonSecurityProperties.getLoginUrl());
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sp)
            throws Exception {
        //cas认证过滤器，当触发本filter时，对ticket进行认证
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sp);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(casSecurityProperties.getLoginUrl());
        entryPoint.setServiceProperties(serviceProperties);
        return entryPoint;
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casSecurityProperties.getHostUrl());
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(
            AuthenticationUserDetailsService<CasAssertionAuthenticationToken> userDetailsService,
            ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator) {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setKey("casProvider");
        provider.setServiceProperties(serviceProperties);
        provider.setTicketValidator(ticketValidator);
        provider.setAuthenticationUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    public LogoutFilter logoutFilter() {
        String logoutRedirectPath = casSecurityProperties.getLogoutUrl() + "?service=" +
                halcyonSecurityProperties.getHostUrl();
        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(halcyonSecurityProperties.getLogoutUrl());
        return logoutFilter;
    }

}

package com.marshal.halcyon.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2018/11/28
 * @desc: halcyon spring security 相关配置属性
 * @ConfigurationProperties注解从yml中直接读取属性
 */
@ConfigurationProperties(prefix = "halcyon.security")
public class HalcyonSecurityProperties {

    private String loginUrl;

    private String logoutUrl;

    private String loginPage;

    private String defaultTargetUrl;

    private String authenticationFailureUrl;

    private String staticResources;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getDefaultTargetUrl() {
        return defaultTargetUrl;
    }

    public void setDefaultTargetUrl(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }

    public String getAuthenticationFailureUrl() {
        return authenticationFailureUrl;
    }

    public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
        this.authenticationFailureUrl = authenticationFailureUrl;
    }

    public String getStaticResources() {
        return staticResources;
    }

    public void setStaticResources(String staticResources) {
        this.staticResources = staticResources;
    }
}

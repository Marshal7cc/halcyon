package com.marshal.halcyon.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auth: Marshal
 * @date: 2019/2/24
 * @desc:
 */
@ConfigurationProperties(prefix = "security.cas.server")
public class CasSecurityProperties {

    private String hostUrl;

    private String loginUrl;

    private String logoutUrl;

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

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }
}

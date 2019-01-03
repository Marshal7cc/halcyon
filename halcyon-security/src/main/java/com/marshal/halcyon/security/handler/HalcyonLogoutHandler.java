package com.marshal.halcyon.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth: Marshal
 * @date: 2018/11/29
 * @desc: 登出处理器
 */
public class HalcyonLogoutHandler implements LogoutHandler {

    private SessionRegistry sessionRegistry;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String sessionId = httpServletRequest.getRequestedSessionId();
        sessionRegistry.removeSessionInformation(sessionId);
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }
}

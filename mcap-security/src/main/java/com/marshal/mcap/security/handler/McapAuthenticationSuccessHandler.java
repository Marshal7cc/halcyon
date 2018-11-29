package com.marshal.mcap.security.handler;

import com.marshal.mcap.security.domain.McapUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auth: Marshal
 * @Date: 2018/11/15
 * @Desc:
 */
public class McapAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private SessionRegistry sessionRegistry;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //web认证详情
        WebAuthenticationDetails authDetails = (WebAuthenticationDetails)authentication.getDetails();
        String remoteAddress = authDetails.getRemoteAddress();

        Object principal = authentication.getPrincipal();
        if(principal instanceof McapUserDetails){
            McapUserDetails userDetails = (McapUserDetails)principal;
            String username = userDetails.getUsername();
            /**
             * 在session中存入信息
             */
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username",username);
        }

        String sessionId = authDetails.getSessionId();
        sessionRegistry.removeSessionInformation(sessionId);
        sessionRegistry.registerNewSession(sessionId,principal);

        handle(httpServletRequest,httpServletResponse,authentication);
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }
}

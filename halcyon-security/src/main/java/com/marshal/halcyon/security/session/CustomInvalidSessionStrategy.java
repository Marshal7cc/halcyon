package com.marshal.halcyon.security.session;

import com.marshal.halcyon.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2019/4/9
 * @desc: 处理 session 失效,跳转到logout清除session
 */
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    @Autowired
    RedirectStrategy redirectStrategy;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, securityProperties.getLogoutUrl());
    }
}

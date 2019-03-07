package com.marshal.halcyon.security.handler;

import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2018/11/29
 * @desc: 认证失败处理器
 */
public class CustomAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        if(RequestHelper.isAjaxRequest(httpServletRequest)){
            httpServletResponse.getWriter().write("没有权限！");
        }else {
            httpServletResponse.sendRedirect(securityProperties.getLoginPage());
        }
    }
}

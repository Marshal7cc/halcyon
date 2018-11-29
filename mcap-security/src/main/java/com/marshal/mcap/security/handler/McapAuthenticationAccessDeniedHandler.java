package com.marshal.mcap.security.handler;

import com.marshal.mcap.core.util.RequestHelper;
import com.marshal.mcap.security.properties.McapSecurityProperties;
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
public class McapAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    McapSecurityProperties mcapSecurityProperties;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        if(RequestHelper.isAjaxRequest(httpServletRequest)){
            httpServletResponse.getWriter().write("没有权限！");
        }else {
            httpServletResponse.sendRedirect(mcapSecurityProperties.getLoginPage());
        }
    }
}

package com.marshal.halcyon.security.handler;

import com.marshal.halcyon.base.account.entity.SysUser;
import com.marshal.halcyon.base.account.service.SysUserService;
import com.marshal.halcyon.base.hr.entity.HrEmployee;
import com.marshal.halcyon.base.hr.service.HrEmployeeService;
import com.marshal.halcyon.core.listener.ContextRefreshedListener;
import com.marshal.halcyon.core.util.ApplicationContextHolder;

import com.marshal.halcyon.security.domain.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @auth: Marshal
 * @Date: 2018/11/15
 * @Desc:
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private HrEmployeeService employeeService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //web认证详情
        WebAuthenticationDetails authDetails = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = authDetails.getRemoteAddress();

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            String username = userDetails.getUsername();
            /**
             * 在session中存入信息
             */
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userId", userDetails.getUserId());
            HrEmployee employee = employeeService.selectByUserId(userDetails.getUserId());
            if (employee != null) {
                session.setAttribute("employeeId", employee.getEmployeeId());
                session.setAttribute("employeeCode", employee.getEmployeeCode());
            }
            /**
             * 更新最近登录时间
             */
            SysUser sysUser = sysUserService.selectByPrimaryKey(userDetails.getUserId());
            sysUser.setLastLoginDate(new Date());
            sysUserService.updateByPrimaryKeySelective(sysUser);
        }

        handle(httpServletRequest, httpServletResponse, authentication);
    }

}

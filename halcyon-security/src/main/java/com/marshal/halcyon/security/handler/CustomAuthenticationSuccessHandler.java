package com.marshal.halcyon.security.handler;

import com.marshal.halcyon.core.listener.ContextRefreshedListener;
import com.marshal.halcyon.core.util.ApplicationContextHolder;
import com.marshal.halcyon.hr.entity.HrEmployee;
import com.marshal.halcyon.hr.service.HrEmployeeService;
import com.marshal.halcyon.security.domain.CustomUserDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

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
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements ContextRefreshedListener {

    private SessionRegistry sessionRegistry;

    private HrEmployeeService employeeService;

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
        }

//        String sessionId = authDetails.getSessionId();
//        sessionRegistry.removeSessionInformation(sessionId);
//        sessionRegistry.registerNewSession(sessionId, principal);
//        httpServletRequest.getRequestDispatcher("halcyon/login").forward(httpServletRequest, httpServletResponse);
        handle(httpServletRequest, httpServletResponse, authentication);
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        this.employeeService = ApplicationContextHolder.getBean(HrEmployeeService.class);
    }
}

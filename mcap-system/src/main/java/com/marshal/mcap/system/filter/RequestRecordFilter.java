package com.marshal.mcap.system.filter;

import com.marshal.mcap.core.beans.SysRequestInfo;
import com.marshal.mcap.core.util.RequestHelper;
import com.marshal.mcap.message.component.MessagePublisher;
import com.marshal.mcap.system.service.SysRequestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: 系统过滤器，记录请求信息、成功失败
 */
@Component
@WebFilter(filterName = "requestRecordFilter", urlPatterns = {"/*"})
@Order(value = 1)
public class RequestRecordFilter implements Filter {

    private static final String channel = "mcap:sysRequestInfo";

    @Autowired
    MessagePublisher messagePublisher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String url = request.getRequestURI();
        if (url.endsWith(".html") || url.split("\\.").length==1) {
            try {
                Long startTime = System.currentTimeMillis();
                filterChain.doFilter(servletRequest, servletResponse);
                Long endTime = System.currentTimeMillis();
                SysRequestInfo sysRequestInfo = RequestHelper.getRequestInfo(request);
                sysRequestInfo.setDuration(endTime - startTime);
                sysRequestInfo.setIsSuccess("Y");
                messagePublisher.publish(RequestRecordFilter.channel, sysRequestInfo);
            } catch (Exception e) {
                SysRequestInfo sysRequestInfo = RequestHelper.getRequestInfo(request);
                sysRequestInfo.setIsSuccess("N");
                messagePublisher.publish(RequestRecordFilter.channel, sysRequestInfo);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {

    }
}

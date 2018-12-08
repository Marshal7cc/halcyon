package com.marshal.mcap.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.marshal.mcap.system.entity.SysRequestInfo;
import com.marshal.mcap.core.util.RequestHelper;
import com.marshal.mcap.message.component.impl.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

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
        if (url.endsWith(".html") || url.split("\\.").length == 1) {
            try {
                Long startTime = System.currentTimeMillis();
                filterChain.doFilter(servletRequest, servletResponse);
                Long endTime = System.currentTimeMillis();
                Map<String, String> map = RequestHelper.getSysRequestInfo(request);
                SysRequestInfo sysRequestInfo = JSONObject.parseObject(JSONObject.toJSONString(map)).toJavaObject(SysRequestInfo.class);
                sysRequestInfo.setDuration(endTime - startTime);
                sysRequestInfo.setIsSuccess("Y");
                messagePublisher.publish(RequestRecordFilter.channel, sysRequestInfo);
            } catch (Exception e) {
                Map<String, String> map = RequestHelper.getSysRequestInfo(request);
                SysRequestInfo sysRequestInfo = JSONObject.parseObject(JSONObject.toJSONString(map)).toJavaObject(SysRequestInfo.class);
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

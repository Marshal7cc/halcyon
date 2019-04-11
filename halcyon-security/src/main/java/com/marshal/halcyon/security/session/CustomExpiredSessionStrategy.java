package com.marshal.halcyon.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.core.constants.BaseConstants;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth: Marshal
 * @date: 2019/4/9
 * @desc: session过期处理策略
 * * 处理 session过期
 * * 导致 session 过期的原因有：
 * * 1. 并发登录控制
 * * 2. 被踢出
 */
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse response = RequestHelper.getHttpServletResponse();
        response.setContentType(BaseConstants.JSON_UTF8);
        response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.responseErr("登录已失效!")));
    }
}

package com.marshal.mcap.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth: Marshal
 * @date: 2018/11/29
 * @desc:请求相关的工具类
 */
public class RequestHelper {

    /**
     * 判断是否为ajax请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}

package com.marshal.halcyon.core.util;

import com.marshal.halcyon.core.entity.ResponseData;
import org.apache.commons.lang3.StringUtils;

/**
 * @auth: Marshal
 * @date: 2019/2/6
 * @desc: 通用返回数据工具
 */
public class ResponseUtils {

    private static final String DEFAULT_RESPONSE_SUCCESS_MSG = "操作成功!";

    private static final String DEFAULT_RESPONSE_FAIL_MSG = "操作失败!";


    public static ResponseData responseOk() {
        return responseOk("");
    }

    public static ResponseData responseOk(String msg) {
        ResponseData responseData = new ResponseData(true, StringUtils.isNotEmpty(msg) ? msg : DEFAULT_RESPONSE_SUCCESS_MSG);
        return responseData;
    }

    public static ResponseData responseErr() {
        return responseErr("");
    }

    public static ResponseData responseErr(String msg) {
        ResponseData responseData = new ResponseData(false, StringUtils.isNotEmpty(msg) ? msg : DEFAULT_RESPONSE_FAIL_MSG);
        return responseData;
    }
}

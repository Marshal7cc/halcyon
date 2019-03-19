package com.marshal.halcyon.core.controller;

import com.marshal.halcyon.core.component.CommonValidator;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 基础controller
 */
public class BaseController {

    @Autowired
    private CommonValidator validator;

    public CommonValidator getValidator() {
        return validator;
    }

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData handleException(Exception e) {
        e.printStackTrace();
        return ResponseUtil.responseErr(e.getMessage());
    }
}

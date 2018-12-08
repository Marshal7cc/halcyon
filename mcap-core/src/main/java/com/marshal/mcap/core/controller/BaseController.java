package com.marshal.mcap.core.controller;

import com.marshal.mcap.core.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;

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
}

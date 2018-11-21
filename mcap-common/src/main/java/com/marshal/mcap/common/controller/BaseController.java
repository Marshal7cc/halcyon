package com.marshal.mcap.common.controller;

import com.marshal.mcap.core.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/27
 * Time: 22:16
 * Description:基础controller
 */
public class BaseController {
    @Autowired
    private CommonValidator validator;

    public CommonValidator getValidator(){
        return validator;
    }
}

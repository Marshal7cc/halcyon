package com.marshal.mcap.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.marshal.mcap.core.beans.ResponseData;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/27
 * Time: 14:10
 * Description:自定义json转换方式注册
 */

public class JacksonMapper extends ObjectMapper {
    public JacksonMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(ResponseData.class,new ResponseDataSerializer());
//        module.addSerializer(DataResponse.class,new DataResponseSerializer());
        this.registerModule(module);
    }
}

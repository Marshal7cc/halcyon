package com.marshal.halcyon.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.marshal.halcyon.core.component.ResponseData;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 自定义objectMapper
 */

public class JacksonMapper extends ObjectMapper {
    public JacksonMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(ResponseData.class, new ResponseDataSerializer());
//        module.addSerializer(DataResponse.class,new DataResponseSerializer());
        this.registerModule(module);
    }
}

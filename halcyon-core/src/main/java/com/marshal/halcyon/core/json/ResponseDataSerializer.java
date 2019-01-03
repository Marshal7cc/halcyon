package com.marshal.halcyon.core.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.marshal.halcyon.core.component.ResponseData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 自定义序列化器
 */
public class ResponseDataSerializer extends JsonSerializer<ResponseData> {
    @Override
    public void serialize(ResponseData responseData, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(responseData!=null){
            Map<String,Object> result = new HashMap<>();
            result.put("total",responseData.getTotal());
            result.put("rows",responseData.getRows());
            result.put("success",responseData.isSuccess());
            result.put("message",responseData.getMessage());
            jsonGenerator.writeObject(result);
        }else {
            jsonGenerator.writeObject(JSON.toJSON(responseData));
        }
    }
}

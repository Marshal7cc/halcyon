package com.marshal.halcyon.workflow.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.activiti.rest.common.api.DataResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2018/10/29
 * @desc: activiti返回数据格式自定义序列化器
 */
public class DataResponseSerializer extends JsonSerializer<DataResponse> {
    @Override
    public void serialize(DataResponse dataResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dataResponse != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("total", dataResponse.getTotal());
            result.put("rows", dataResponse.getData());
            result.put("success", true);
            result.put("message", "success");
            jsonGenerator.writeObject(result);
        } else {
            jsonGenerator.writeObject(JSON.toJSON(dataResponse));
        }
    }
}

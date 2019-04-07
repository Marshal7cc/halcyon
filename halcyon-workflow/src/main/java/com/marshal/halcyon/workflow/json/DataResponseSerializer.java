package com.marshal.halcyon.workflow.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.activiti.rest.common.api.DataResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
            List<Map> rows = JSONObject.parseObject(JSON.toJSONString(dataResponse.getData(), SerializerFeature.WriteDateUseDateFormat), new TypeReference<List<Map>>() {
            });
            result.put("rows", rows);
            result.put("success", true);
            result.put("message", "success");
            jsonGenerator.writeObject(result);
        } else {
            jsonGenerator.writeObject(ResponseUtil.responseErr("数据为空!"));
        }
    }
}

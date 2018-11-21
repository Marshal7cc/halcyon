package com.marshal.mcap.core.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.marshal.mcap.core.beans.ResponseData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/10/27
 * Time: 14:15
 * Description:
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

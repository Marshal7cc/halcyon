package com.marshal.halcyon.function.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.function.entity.SysRequestInfo;
import com.marshal.halcyon.message.redis.component.SysRequestMessageSubscriber;
import com.marshal.halcyon.function.mapper.SysRequestInfoMapper;
import com.marshal.halcyon.function.service.SysRequestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @auth: Marshal
 * @date: 2018/12/7
 * @desc:
 */
@Service
public class SysRequestInfoServiceImpl extends BaseServiceImpl<SysRequestInfo> implements SysRequestInfoService {

    @Autowired
    SysRequestInfoMapper sysRequestInfoMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 记录系统请求，每小时执行一次
     */
    @Override
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void recordSysRequest() {
        Map<Object, Object> requestMap = redisTemplate.opsForHash().entries(SysRequestMessageSubscriber.h);
        Iterator iter = requestMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String requestStr = (String) entry.getValue();
            JSONObject json = JSONObject.parseObject(requestStr);
            SysRequestInfo sysRequestInfo = json.toJavaObject(SysRequestInfo.class);
            sysRequestInfoMapper.insert(sysRequestInfo);
        }
        redisTemplate.delete(SysRequestMessageSubscriber.h);

    }
}

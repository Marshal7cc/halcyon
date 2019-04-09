package com.marshal.halcyon.core.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.marshal.halcyon.core.annotation.AccessLimit;
import com.marshal.halcyon.core.constants.BaseConstants;
import com.marshal.halcyon.core.entity.AccessLimitType;
import com.marshal.halcyon.core.exception.AccessLimitException;
import com.marshal.halcyon.core.util.IPUtil;
import com.marshal.halcyon.core.util.RequestHelper;
import com.marshal.halcyon.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @auth: Marshal
 * @date: 2019/4/9
 * @desc: 限流切面
 */
@Aspect
@Component
@Slf4j
public class AccessLimitAspect {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Pointcut("@annotation(com.marshal.halcyon.core.annotation.AccessLimit)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = RequestHelper.getHttpServletRequest();
        HttpServletResponse response = RequestHelper.getHttpServletResponse();

        //方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AccessLimit limitAnnotation = method.getAnnotation(AccessLimit.class);
        AccessLimitType accessLimitType = limitAnnotation.limitType();
        String key;
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        switch (accessLimitType) {
            case IP:
                key = IPUtil.getIpAddr(request);
                break;
            case CUSTOM:
                key = limitAnnotation.key();
                break;
            default:
                key = "";
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix() + ":", method.getDeclaringClass().toString() + "." + method.getName() + ":" + key + "_" + request.getRequestedSessionId()));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        log.info("第{}次访问key为 {}，描述为 [] 的接口", count, keys);
        if (count != null && count.intValue() <= limitCount) {
            return point.proceed();
        } else {
            response.setContentType(BaseConstants.JSON_UTF8);
            response.getWriter().write(objectMapper.writeValueAsString(ResponseUtil.responseErr("接口访问超出频率限制")));
            throw new AccessLimitException("接口访问超出频率限制");
        }

    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

}

package com.marshal.halcyon.core.annotation;

import com.marshal.halcyon.core.entity.AccessLimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    // redis key prefix
    String prefix() default "halcyon:cache:access_limit";

    // redis key,不同接口之间的key不应重复
    String key() default "";

    // 时间限制，单位秒
    int period();

    // 限制访问次数
    int count();

    // 限制类型
    AccessLimitType limitType() default AccessLimitType.CUSTOM;
}

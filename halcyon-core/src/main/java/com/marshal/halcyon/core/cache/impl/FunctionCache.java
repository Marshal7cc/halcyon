package com.marshal.halcyon.core.cache.impl;

import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/6/26
 * @desc:
 */
@Component
public class FunctionCache<SysFunction> extends RedisCache<SysFunction> {

    private String SQI_ID = "com.marshal.halcyon.base.function.mapper.SysFunctionMapper.select";

    private static final String KEY = "halcyon:cache:function";

    private static final String HASH_KEY_COLUMN = "functionId";

    @Override
    String getSqlId() {
        return SQI_ID;
    }

    @Override
    String getKeyName() {
        return KEY;
    }

    @Override
    String getHashKeyColumn() {
        return HASH_KEY_COLUMN;
    }
}

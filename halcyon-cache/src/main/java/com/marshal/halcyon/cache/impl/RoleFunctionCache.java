/*
 * #{copyright}#
 */
package com.marshal.halcyon.cache.impl;

import com.alibaba.fastjson.JSON;
import com.marshal.halcyon.system.entity.SysFunction;
import com.marshal.halcyon.system.mapper.SysFunctionMapper;
import com.marshal.halcyon.system.service.SysFunctionService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleFunctionCache extends HashStringRedisCache<Long[]> {

    private String roleFunctionQuerySqlId = SysFunctionMapper.class.getName() + ".testCache";

    @Autowired
    SysFunctionService sysFunctionService;

    private final Logger logger = LoggerFactory.getLogger(RoleFunctionCache.class);

    {
        setLoadOnStartUp(true);
        setType(Long[].class);
    }


    /**
     * key 为roleId.
     *
     * @param key roleId
     * @return values
     */
    @Override
    public Long[] getValue(String key) {
        return super.getValue(key);
    }

    /**
     * @param key    code.lang
     * @param values values
     */
    @Override
    public void setValue(String key, Long[] values) {
        super.setValue(key, values);
    }

    @SuppressWarnings("unchecked")
    protected void initLoad() {
        Map<String, Set<Long>> roleFunctions = new HashMap<>();
        try {
            List<SysFunction> menuList = sysFunctionService.getMenus();
            menuList.forEach(item -> {
                getRedisTemplate().opsForHash().put(getCategory() + getName(), item.getFunctionName(), JSON.toJSONString(item));
            });
//            sqlSession.select(roleFunctionQuerySqlId, (resultContext) -> {
//                Map<String, Object> value = (Map<String, Object>) resultContext.getResultObject();
//                String roleId = "" + value.get("ROLE_ID");
//                Set<Long> sets = roleFunctions.get(roleId);
//                if (sets == null) {
//                    sets = new HashSet<>();
//                    roleFunctions.put(roleId, sets);
//                }
//                Long resourceId = ((Number) value.get("FUNCTION_ID")).longValue();
//                sets.add(resourceId);
//            });
//
//            roleFunctions.forEach((k, v) -> {
//                setValue(k, v.toArray(new Long[v.size()]));
//            });
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("init role function cache exception: ", e);
            }
        }
    }
}

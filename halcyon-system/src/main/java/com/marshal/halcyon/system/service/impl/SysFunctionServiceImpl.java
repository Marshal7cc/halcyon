package com.marshal.halcyon.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;

import com.marshal.halcyon.system.entity.SysFunction;
import com.marshal.halcyon.system.mapper.SysFunctionMapper;
import com.marshal.halcyon.system.service.SysFunctionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/5
 * Time: 22:03
 * Description:
 */
@Service
public class SysFunctionServiceImpl implements SysFunctionService {

    @Autowired
    SysFunctionMapper sysFunctionMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysFunctionMapper.selectFunctions(condition);
    }

    @Override
    public SysFunction selectByPrimaryKey(Long id) {
        return sysFunctionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(SysFunction sysFunction) {
        if (sysFunction.getFunctionId() == null) {
            sysFunctionMapper.insertSelective(sysFunction);
        } else {
            sysFunctionMapper.updateByPrimaryKeySelective(sysFunction);
        }
    }

    @Override
    public void delete(Long[] idList) {
        for (Long id : idList) {
            sysFunctionMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> getFunctionOptions() {
        return sysFunctionMapper.getFunctionOptions();
    }

    @Override
    public List<SysFunction> getMenus() {
        Map<Object, Object> requestMap = redisTemplate.opsForHash().entries("halcyon:cacheroleFunctionCache");
        if (requestMap != null) {
            List<SysFunction> topFunctionList = new ArrayList<>();
            Iterator iter = requestMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String string = (String) entry.getValue();
                SysFunction sysFunction = JSON.parseObject(string, new TypeReference<SysFunction>() {
                });
                topFunctionList.add(sysFunction);
            }
            return topFunctionList;
        }
        List<SysFunction> topFunctionList = selectTopFunctions();
        getChildFunctions(topFunctionList);
        return topFunctionList;
    }

    /**
     * 生成首页菜单
     *
     * @return
     */
    @Override
    public List<SysFunction> selectTopFunctions() {
        return sysFunctionMapper.selectTopFunctions();
    }

    @Override
    public List<SysFunction> selectChildFunctions(Long functionId) {
        return sysFunctionMapper.selectChildFunctions(functionId);
    }

    /**
     * 递归填充最外层菜单的子菜单
     *
     * @param functionList
     * @return
     */
    public List<SysFunction> getChildFunctions(List<SysFunction> functionList) {
        for (SysFunction item : functionList) {
            List<SysFunction> childList = selectChildFunctions(item.getFunctionId());
            if (childList != null && childList.size() > 0) {
                item.setChildFunctions(childList);
                getChildFunctions(childList);
            } else {
                continue;
            }
        }
        return functionList;
    }
}

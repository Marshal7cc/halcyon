package com.marshal.halcyon.base.function.service.impl;

import com.github.pagehelper.PageHelper;

import com.marshal.halcyon.base.account.entity.SysRoleFunction;
import com.marshal.halcyon.base.account.mapper.SysRoleFunctionMapper;
import com.marshal.halcyon.base.function.entity.SysFunction;
import com.marshal.halcyon.base.function.mapper.SysFunctionMapper;
import com.marshal.halcyon.base.function.service.SysFunctionService;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
public class SysFunctionServiceImpl extends BaseServiceImpl<SysFunction> implements SysFunctionService {

    @Autowired
    SysFunctionMapper sysFunctionMapper;

    @Autowired
    SysRoleFunctionMapper sysRoleFunctionMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    //menus start
    @Override
    public List<SysFunction> getMenus(Long roleId) {
        List<SysFunction> topFunctionList = selectTopFunctions(roleId);
        getChildFunctions(roleId, topFunctionList);
        return topFunctionList;
    }

    private List<SysFunction> selectTopFunctions(Long roleId) {
        return sysFunctionMapper.selectTopFunctions(roleId);
    }

    private List<SysFunction> selectChildFunctions(Long roleId, Long functionId) {
        return sysFunctionMapper.selectChildFunctions(roleId, functionId);
    }

    private List<SysFunction> getChildFunctions(Long roleId, List<SysFunction> functionList) {
        for (SysFunction item : functionList) {
            List<SysFunction> childList = selectChildFunctions(roleId, item.getFunctionId());
            if (childList != null && childList.size() > 0) {
                item.setChildFunctions(childList);
                getChildFunctions(roleId, childList);
            } else {
                continue;
            }
        }
        return functionList;
    }
    //menus end

    @Override
    public List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysFunctionMapper.selectFunctions(condition);
    }

    @Override
    public List<Map> getFunctionOptions() {
        return sysFunctionMapper.getFunctionOptions();
    }

    @Override
    public List<Map> selectRoleFunctionAssignList(Long roleId) {
        return sysFunctionMapper.selectRoleFunctionAssignList(roleId);
    }

    @Override
    public List<SysRoleFunction> updateSysRoleFunctions(List<SysRoleFunction> sysRoleFunctions) {
        if (sysRoleFunctions.size() > 0) {
            Long roleId = sysRoleFunctions.get(0).getRoleId();
            SysRoleFunction condition = new SysRoleFunction();
            condition.setRoleId(roleId);
            List<SysRoleFunction> recentRoleFunctions = sysRoleFunctionMapper.select(condition);
            for (SysRoleFunction recentRoleFunction : recentRoleFunctions) {
                sysRoleFunctionMapper.deleteByPrimaryKey(recentRoleFunction);
            }

            for (SysRoleFunction sysRoleFunction : sysRoleFunctions) {
                sysRoleFunctionMapper.insertSelective(sysRoleFunction);
            }
        }
        return sysRoleFunctions;
    }
}

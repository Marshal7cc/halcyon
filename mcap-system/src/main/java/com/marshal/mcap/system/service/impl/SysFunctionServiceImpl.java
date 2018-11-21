package com.marshal.mcap.system.service.impl;

import com.github.pagehelper.PageHelper;

import com.marshal.mcap.system.entity.SysFunction;
import com.marshal.mcap.system.mapper.SysFunctionMapper;
import com.marshal.mcap.system.service.SysFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysFunctionServiceImpl implements SysFunctionService {

    @Autowired
    SysFunctionMapper sysFunctionMapper;

    @Override
    public List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return sysFunctionMapper.selectFunctions(condition);
    }

    @Override
    public SysFunction selectByPrimaryKey(Long id) {
        return sysFunctionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(SysFunction sysFunction) {
        if(sysFunction.getFunctionId()==null){
            sysFunctionMapper.insertSelective(sysFunction);
        }else {
            sysFunctionMapper.updateByPrimaryKeySelective(sysFunction);
        }
    }

    @Override
    public void delete(Long[] idList) {
        for(Long id :idList){
            sysFunctionMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> getFunctionOptions() {
        return sysFunctionMapper.getFunctionOptions();
    }

    /**
     * 生成首页菜单
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
}

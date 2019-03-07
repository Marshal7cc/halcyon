package com.marshal.halcyon.function.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.function.entity.SysResource;
import com.marshal.halcyon.function.mapper.SysResourceMapper;
import com.marshal.halcyon.function.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/5
 * Time: 22:01
 * Description:
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> select(SysResource condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysResourceMapper.select(condition);
    }

    @Override
    public SysResource selectByPrimaryKey(Long id) {
        return sysResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(SysResource sysResource) {
        if (sysResource.getResourceId() == null) {
            sysResourceMapper.insertSelective(sysResource);
        } else {
            sysResourceMapper.updateByPrimaryKeySelective(sysResource);
        }
    }

    @Override
    public void delete(Long[] idList) {
        for (Long id : idList) {
            sysResourceMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 获取下拉框数据
     * @return
     */
    @Override
    public List<Map> getResourceOptions() {
        return sysResourceMapper.getResourceOptions();
    }
}

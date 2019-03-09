package com.marshal.halcyon.function.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
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
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    /**
     * 获取下拉框数据
     *
     * @return
     */
    @Override
    public List<Map> getResourceOptions() {
        return sysResourceMapper.getResourceOptions();
    }
}

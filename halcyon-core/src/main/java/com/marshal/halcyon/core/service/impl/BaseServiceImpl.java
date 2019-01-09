package com.marshal.halcyon.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.BaseService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/1/7
 * @desc: 通用Service
 */

public class BaseServiceImpl<T> implements BaseService<T> {

    Mapper<T> mapper;

    @Override
    public List<T> select(T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(condition);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectByPrimaryKey(T record) {
        return mapper.selectByPrimaryKey(record);
    }

    @Override
    public T insert(T record) {
        mapper.insert(record);
        return record;
    }

    @Override
    public T insertSelective(T record) {
        mapper.insertSelective(record);
        return record;
    }

    @Override
    public T updateByPrimaryKey(T record) {
        return record;
    }

    @Override
    public T updateByPrimaryKeySelective(T record) {
        return record;
    }

    @Override
    public boolean checkCAS(T record) {
        T dto = mapper.selectByPrimaryKey(record);
        //todo:version乐观锁
        return false;
    }

    @Override
    public T deleteByPrimaryKey(T record) {
        mapper.deleteByPrimaryKey(record);
        return record;
    }
}

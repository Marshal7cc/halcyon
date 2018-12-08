package com.marshal.mcap.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.mcap.core.service.BaseService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/2
 * @desc: 通用Service方法
 * service方法可以继承该方法，调用泛型方法进行增删改查，目前暂未使用
 */

public class BaseServiceImpl<T> implements BaseService<T> {

    Mapper<T> mapper;

    @Override
    public List<T> select(T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(condition);
    }

    @Override
    public T selectByPrimaryKey(T record) {
        return mapper.selectByPrimaryKey(record);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(T record) {
        return mapper.deleteByPrimaryKey(record);
    }
}

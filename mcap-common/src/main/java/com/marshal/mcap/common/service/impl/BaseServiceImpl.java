package com.marshal.mcap.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.mcap.common.service.BaseService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/2
 * Time: 22:10
 * Description:通用Service方法.
 * service方法可以继承该方法，调用泛型方法进行增删改查，本项目未使用
 * 使用的通用mapper
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

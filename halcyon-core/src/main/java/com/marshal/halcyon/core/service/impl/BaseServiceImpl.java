package com.marshal.halcyon.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.core.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/1/7
 * @desc: common service
 */
@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    Mapper<T> mapper;

    @Override
    public List<T> select(T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(condition);
    }

    @Override
    public List<T> select(T condition) {
        return mapper.select(condition);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectOne(T condition) {
        return mapper.selectOne(condition);
    }

    @Override
    public T selectByPrimaryKey(Object record) {
        return mapper.selectByPrimaryKey(record);
    }

    @Override
    public int insert(T record) {
        record = (T) DtoUtil.setCreateAttribute(record);
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        record = (T) DtoUtil.setCreateAttribute(record);
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        record = (T) DtoUtil.setUpdateAttribute(record);
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        record = (T) DtoUtil.setUpdateAttribute(record);
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Object record) {
        return mapper.deleteByPrimaryKey(record);
    }

    @Override
    public int save(T record) {
        if (DtoUtil.isPrimaryKeyNull(record)) {
            return this.insertSelective(record);
        } else {
            return this.updateByPrimaryKey(record);
        }
    }

    @Override
    public void batchDelete(Object[] keys) {
        for (Object primaryKey : keys) {
            this.deleteByPrimaryKey(primaryKey);
        }
    }

    @Override
    public void batchDelete(List<T> records) {
        records.forEach(item -> this.deleteByPrimaryKey(item));
    }
}

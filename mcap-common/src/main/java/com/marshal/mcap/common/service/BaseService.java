package com.marshal.mcap.common.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseService<T>{
    /**
     *与mapper同步的方法
     */
    List<T> select(T condition, int pageNum, int pageSize);

    T selectByPrimaryKey(T record);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKey(T record);

    @Transactional(rollbackFor = Exception.class)
    int updateByPrimaryKeySelective(T record);

    int deleteByPrimaryKey(T record);
}

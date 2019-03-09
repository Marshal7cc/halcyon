package com.marshal.halcyon.core.service;

import java.util.List;

public interface BaseService<T> {

    List<T> select(T condition, int pageNum, int pageSize);

    List<T> select(T condition);

    List<T> selectAll();

    T selectOne(T condition);

    T selectByPrimaryKey(Object record);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int deleteByPrimaryKey(Object record);

    void batchDelete(Object[] keys);

    void batchDelete(List<T> records);

    int save(T records);

}

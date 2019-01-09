package com.marshal.halcyon.core.service;

import java.util.List;

public interface BaseService<T> {

    List<T> select(T condition, int pageNum, int pageSize);

    List<T> selectAll();

    T selectByPrimaryKey(T record);

    T insert(T record);

    T insertSelective(T record);

    T updateByPrimaryKey(T record);

    T updateByPrimaryKeySelective(T record);

    /**
     * 乐观锁检查
     *
     * @param record
     * @return
     */
    boolean checkCAS(T record);

    T deleteByPrimaryKey(T record);

}

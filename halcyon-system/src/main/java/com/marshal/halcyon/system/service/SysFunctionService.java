package com.marshal.halcyon.system.service;


import com.marshal.halcyon.system.entity.SysFunction;

import java.util.List;
import java.util.Map;

public interface SysFunctionService {
    List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize);

    SysFunction selectByPrimaryKey(Long id);

    void save(SysFunction sysFunction);

    void delete(Long[] idList);

    List<Map> getFunctionOptions();

    List<SysFunction> selectTopFunctions();

    List<SysFunction> selectChildFunctions(Long functionId);
}

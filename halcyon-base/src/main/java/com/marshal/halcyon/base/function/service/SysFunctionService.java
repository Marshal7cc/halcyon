package com.marshal.halcyon.base.function.service;


import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.function.entity.SysFunction;

import java.util.List;
import java.util.Map;

public interface SysFunctionService extends BaseService<SysFunction> {

    List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize);

    List<Map> getFunctionOptions();

    List<SysFunction> getMenus();

    List<SysFunction> selectTopFunctions();

    List<SysFunction> selectChildFunctions(Long functionId);

    List<SysFunction> getChildFunctions(List<SysFunction> topFunctionList);

    List<Map> selectRoleFunctionAssignList(Long roleId);

}

package com.marshal.halcyon.base.function.service;


import com.marshal.halcyon.base.account.entity.SysRoleFunction;
import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.function.entity.SysFunction;

import java.util.List;
import java.util.Map;

public interface SysFunctionService extends BaseService<SysFunction> {

    List<SysFunction> selectFunctions(SysFunction condition, int pageNum, int pageSize);

    List<Map> getFunctionOptions();

    List<SysFunction> getMenus(Long roleId);

    List<Map> selectRoleFunctionAssignList(Long roleId);

    List<SysRoleFunction> updateSysRoleFunctions(List<SysRoleFunction> sysRoleFunctions);

}

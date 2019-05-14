package com.marshal.halcyon.base.function.mapper;


import com.marshal.halcyon.base.function.entity.SysFunction;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;
import java.util.Map;

public interface SysFunctionMapper extends Mapper<SysFunction> {

    List<SysFunction> selectTopFunctions(@Param("roleId") Long roleId);

    List<SysFunction> selectChildFunctions(@Param("roleId") Long roleId, @Param("functionId") Long functionId);

    List<Map> getFunctionOptions();

    List<SysFunction> selectFunctions(SysFunction condition);

    List<Map> selectRoleFunctionAssignList(Long roleId);
}
package com.marshal.halcyon.base.function.mapper;


import com.marshal.halcyon.base.function.entity.SysFunction;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;
import java.util.Map;

public interface SysFunctionMapper extends Mapper<SysFunction> {

    List<SysFunction> selectTopFunctions();

    List<SysFunction> selectChildFunctions(Long functionId);

    List<Map> getFunctionOptions();

    List<SysFunction> selectFunctions(SysFunction condition);
}
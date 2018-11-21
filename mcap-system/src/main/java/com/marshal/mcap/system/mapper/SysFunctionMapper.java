package com.marshal.mcap.system.mapper;



import com.marshal.mcap.system.entity.SysFunction;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;
import java.util.Map;

public interface SysFunctionMapper extends Mapper<SysFunction> {

    List<SysFunction> selectTopFunctions();

    List<SysFunction> selectChildFunctions(Long functionId);

    List<Map> getFunctionOptions();

    List<SysFunction> selectFunctions(SysFunction condition);
}
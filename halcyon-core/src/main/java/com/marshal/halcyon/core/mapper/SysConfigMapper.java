package com.marshal.halcyon.core.mapper;

import com.marshal.halcyon.core.entity.SysConfig;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SysConfigMapper extends Mapper<SysConfig> {

    Map getOssConfig();

}
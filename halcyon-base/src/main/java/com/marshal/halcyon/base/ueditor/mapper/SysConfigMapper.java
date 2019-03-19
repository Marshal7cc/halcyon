package com.marshal.halcyon.base.ueditor.mapper;

import com.marshal.halcyon.base.ueditor.entity.SysConfig;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SysConfigMapper extends Mapper<SysConfig> {

    Map getOSSConfig();

}
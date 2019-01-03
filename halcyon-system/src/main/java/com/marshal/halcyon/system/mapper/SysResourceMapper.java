package com.marshal.halcyon.system.mapper;
import com.marshal.halcyon.system.entity.SysResource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysResourceMapper extends Mapper<SysResource> {

    List<Map> getResourceOptions();
}
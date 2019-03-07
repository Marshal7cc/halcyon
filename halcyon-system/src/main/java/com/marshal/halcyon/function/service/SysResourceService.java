package com.marshal.halcyon.function.service;


import com.marshal.halcyon.function.entity.SysResource;

import java.util.List;
import java.util.Map;

public interface SysResourceService {
    List<SysResource> select(SysResource condition, int pageNum, int pageSize);

    SysResource selectByPrimaryKey(Long id);

    void save(SysResource sysResource);

    void delete(Long[] idList);

    List<Map> getResourceOptions();
}

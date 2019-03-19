package com.marshal.halcyon.base.function.service;


import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.function.entity.SysResource;

import java.util.List;
import java.util.Map;

public interface SysResourceService extends BaseService<SysResource> {

    List<Map> getResourceOptions();
}

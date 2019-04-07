package com.marshal.halcyon.base.hr.service;


import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.hr.entity.HrEmployee;

import java.util.List;
import java.util.Map;

public interface HrEmployeeService extends BaseService<HrEmployee> {

    HrEmployee selectByUserId(Long userId);

    List<Map> getEmpOptions();

    List<Map> getEmpCodeOptions();

}

package com.marshal.halcyon.base.hr.service;


import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.hr.entity.HrUnit;

import java.util.List;
import java.util.Map;

public interface HrUnitService extends BaseService<HrUnit> {

    List<Map> getOptions();

}

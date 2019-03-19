package com.marshal.halcyon.base.hr.service;


import com.marshal.halcyon.core.service.BaseService;
import com.marshal.halcyon.base.hr.entity.HrPosition;

import java.util.List;
import java.util.Map;

public interface HrPositionService extends BaseService<HrPosition> {

    List<Map> selectByUnitId(Long unitId);

    List<Map> getParentPositionOptions();

}

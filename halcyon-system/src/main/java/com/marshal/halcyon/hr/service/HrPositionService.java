package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrPosition;

import java.util.List;
import java.util.Map;

public interface HrPositionService {

    List<HrPosition> query(HrPosition condition, int pageNum, int pageSize);

    void submit(HrPosition hrPosition);

    void remove(Long[] idList);

    HrPosition selectByPositionId(Long id);

    List<Map> selectByUnitId(Long unitId);

    List<Map> getParentPositionOptions();

}

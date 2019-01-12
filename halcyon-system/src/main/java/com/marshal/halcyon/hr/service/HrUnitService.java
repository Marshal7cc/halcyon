package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrUnit;

import java.util.List;
import java.util.Map;

public interface HrUnitService {

    List<HrUnit> query(HrUnit condition, int pageNum, int pageSize);

    void submit(HrUnit hrUnit);

    void remove(Long[] idList);

    HrUnit selectByUnitId(Long id);

    List<Map> getOptions();

}

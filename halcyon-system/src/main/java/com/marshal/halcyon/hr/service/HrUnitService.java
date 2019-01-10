package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrUnit;

import java.util.List;

public interface HrUnitService {

    List<HrUnit> query(HrUnit condition, int pageNum, int pageSize);

    public void submit(HrUnit hrUnit);

    public void remove(Long[] idList);

    public HrUnit selectByUnitId(Long id);
}

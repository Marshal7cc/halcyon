package com.marshal.halcyon.hr.service.impl;

import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.hr.entity.HrPosition;
import com.marshal.halcyon.hr.entity.HrUnit;
import com.marshal.halcyon.hr.mapper.HrUnitMapper;
import com.marshal.halcyon.hr.service.HrUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HrUnitServiceImpl extends BaseServiceImpl<HrUnit> implements HrUnitService {

    @Autowired
    HrUnitMapper hrUnitMapper;

    @Override
    public List<Map> getOptions() {
        return hrUnitMapper.getParentUnitOptions();
    }
}

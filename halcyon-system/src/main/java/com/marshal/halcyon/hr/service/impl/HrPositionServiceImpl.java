package com.marshal.halcyon.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.hr.entity.HrPosition;
import com.marshal.halcyon.hr.mapper.HrPositionMapper;
import com.marshal.halcyon.hr.service.HrPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HrPositionServiceImpl extends BaseServiceImpl<HrPosition> implements HrPositionService {

    @Autowired
    HrPositionMapper hrPostionMapper;

    @Override
    public List<Map> selectByUnitId(Long unitId) {
        return hrPostionMapper.selectByUnitId(unitId);
    }

    @Override
    public List<Map> getParentPositionOptions() {
        return hrPostionMapper.getParentPositionOptions();
    }
}

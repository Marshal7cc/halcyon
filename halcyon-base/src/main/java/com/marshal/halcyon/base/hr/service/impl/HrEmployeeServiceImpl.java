package com.marshal.halcyon.base.hr.service.impl;

import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.base.hr.mapper.HrEmployeeMapper;
import com.marshal.halcyon.base.hr.service.HrEmployeeService;
import com.marshal.halcyon.base.hr.entity.HrEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HrEmployeeServiceImpl extends BaseServiceImpl<HrEmployee> implements HrEmployeeService {

    @Autowired
    HrEmployeeMapper hrEmployeeMapper;

    @Override
    public HrEmployee selectByUserId(Long userId) {
        return hrEmployeeMapper.getEmployeeByUserId(userId);
    }

    @Override
    public List<Map> getEmpOptions() {
        return hrEmployeeMapper.getEmpOptions();
    }

    @Override
    public List<Map> getEmpCodeOptions() {
        return hrEmployeeMapper.getEmpCodeOptions();
    }
}

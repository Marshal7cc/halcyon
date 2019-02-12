package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrEmployee;

import java.util.List;
import java.util.Map;

public interface HrEmployeeService {

    List<HrEmployee> query(HrEmployee condition, int pageNum, int pageSize);

    void submit(HrEmployee hrEmployee);

    void remove(Long[] idList);

    HrEmployee selectByEmployeeId(Long id);

    HrEmployee selectByUserId(Long userId);

    List<Map> getEmpOptions();

}

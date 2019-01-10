package com.marshal.halcyon.hr.service;


import com.marshal.halcyon.hr.entity.HrEmployee;

import java.util.List;

public interface HrEmployeeService {

    List<HrEmployee> query(HrEmployee condition, int pageNum, int pageSize);

    public void submit(HrEmployee hrEmployee);

    public void remove(Long[] idList);

    public HrEmployee selectByEmployeeId(Long id);

}

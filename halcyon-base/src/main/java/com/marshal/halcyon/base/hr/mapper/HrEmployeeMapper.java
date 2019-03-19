package com.marshal.halcyon.base.hr.mapper;

import com.marshal.halcyon.base.hr.entity.HrEmployee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HrEmployeeMapper extends Mapper<HrEmployee> {

    List<HrEmployee> query(HrEmployee condition);

    List<Map> getEmpOptions();

    String getEmployeeNameByCode(String employeeCode);

    HrEmployee getEmployeeByUserId(Long userId);

}
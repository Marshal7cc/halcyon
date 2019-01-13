package com.marshal.halcyon.hr.mapper;

import com.marshal.halcyon.hr.entity.HrEmployee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HrEmployeeMapper extends Mapper<HrEmployee> {

    List<HrEmployee> query(HrEmployee condition);

    List<Map> getEmpOptions();

}
package com.marshal.halcyon.hr.mapper;

import com.marshal.halcyon.hr.entity.HrUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HrUnitMapper extends Mapper<HrUnit> {

    List<HrUnit> query(HrUnit condition);

    List<Map> getParentUnitOptions();

}
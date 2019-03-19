package com.marshal.halcyon.base.hr.mapper;

import com.marshal.halcyon.base.hr.entity.HrUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HrUnitMapper extends Mapper<HrUnit> {

    List<HrUnit> query(HrUnit condition);

    List<Map> getParentUnitOptions();

}
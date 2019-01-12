package com.marshal.halcyon.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.hr.mapper.HrUnitMapper;
import com.marshal.halcyon.hr.service.HrUnitService;
import com.marshal.halcyon.hr.entity.HrUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HrUnitServiceImpl implements HrUnitService {

    @Autowired
    HrUnitMapper hrUnitMapper;

    /**
     * 部门信息查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<HrUnit> query(HrUnit condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return hrUnitMapper.select(condition);
    }

    /**
     * 部门信息新增/修改
     *
     * @param hrUnit
     */
    @Override
    public void submit(HrUnit hrUnit) {
        if (hrUnit.getUnitId() != null) {
            hrUnitMapper.updateByPrimaryKey(hrUnit);
        } else {
            hrUnitMapper.insertSelective(hrUnit);
        }
    }

    /**
     * 部门信息删除
     *
     * @param idList
     */
    @Override
    public void remove(Long[] idList) {
        for (Long id : idList) {
            hrUnitMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据部门ID查找部门
     *
     * @param id
     * @return
     */
    @Override
    public HrUnit selectByUnitId(Long id) {
        return hrUnitMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> getOptions() {
        return hrUnitMapper.getParentUnitOptions();
    }
}

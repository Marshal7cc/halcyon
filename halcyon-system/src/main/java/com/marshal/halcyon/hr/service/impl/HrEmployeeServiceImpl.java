package com.marshal.halcyon.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.hr.mapper.HrEmployeeMapper;
import com.marshal.halcyon.hr.service.HrEmployeeService;
import com.marshal.halcyon.hr.entity.HrEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HrEmployeeServiceImpl implements HrEmployeeService {

    @Autowired
    HrEmployeeMapper hrEmployeeMapper;

    /**
     * 员工查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<HrEmployee> query(HrEmployee condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return hrEmployeeMapper.query(condition);
    }

    /**
     * 员工信息新增/修改
     *
     * @param hrEmployee
     */
    @Override
    public void submit(HrEmployee hrEmployee) {
        if (hrEmployee.getEmployeeId() != null) {
            hrEmployeeMapper.updateByPrimaryKey(hrEmployee);
        } else {
            hrEmployeeMapper.insert(hrEmployee);
        }
    }

    /**
     * 员工信息删除
     *
     * @param idList
     */
    @Override
    public void remove(Long[] idList) {
        for (Long id : idList) {
            hrEmployeeMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据员工ID查找员工
     *
     * @param id
     * @return
     */
    @Override
    public HrEmployee selectByEmployeeId(Long id) {
        return hrEmployeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public HrEmployee selectByUserId(Long userId) {
        return hrEmployeeMapper.getEmployeeByUserId(userId);
    }

    @Override
    public List<Map> getEmpOptions() {
        return hrEmployeeMapper.getEmpOptions();
    }
}

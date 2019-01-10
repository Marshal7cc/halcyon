package com.marshal.halcyon.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.hr.entity.HrPosition;
import com.marshal.halcyon.hr.mapper.HrPositionMapper;
import com.marshal.halcyon.hr.service.HrPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrPositionServiceImpl implements HrPositionService {

    @Autowired
    HrPositionMapper hrOrgPostionMapper;

    /**
     * 部门职位信息查询
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<HrPosition> query(HrPosition condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return hrOrgPostionMapper.select(condition);
    }

    /**
     * 部门职位信息新增/修改
     *
     * @param hrOrgPostion
     */
    @Override
    public void submit(HrPosition hrOrgPostion) {
        if (hrOrgPostion.getUnitId() != null) {
            hrOrgPostionMapper.updateByPrimaryKey(hrOrgPostion);
        } else {
            hrOrgPostionMapper.insert(hrOrgPostion);
        }
    }

    /**
     * 部门职位信息删除
     *
     * @param idList
     */
    @Override
    public void remove(Long[] idList) {
        for (Long id : idList) {
            hrOrgPostionMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据职位ID查找部门职位
     *
     * @param id
     * @return
     */
    @Override
    public HrPosition selectByPositionId(Long id) {
        return hrOrgPostionMapper.selectByPrimaryKey(id);
    }

}

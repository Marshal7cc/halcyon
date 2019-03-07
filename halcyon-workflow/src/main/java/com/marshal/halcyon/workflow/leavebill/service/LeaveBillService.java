package com.marshal.halcyon.workflow.leavebill.service;

import com.marshal.halcyon.workflow.leavebill.entity.ActBizLeave;

import java.util.List;

public interface LeaveBillService {

    void createLeaveBillInstance(ActBizLeave actBizLeave);

    List<ActBizLeave> query(ActBizLeave condition);

    List<ActBizLeave> submit(List<ActBizLeave> actBizLeaves);

    List<ActBizLeave> remove(List<ActBizLeave> actBizLeaves);

    ActBizLeave queryById(Long id);
}

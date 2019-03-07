package com.marshal.halcyon.workflow.leavebill.service.impl;

import com.marshal.halcyon.workflow.leavebill.entity.ActBizLeave;
import com.marshal.halcyon.workflow.leavebill.mapper.ActBizLeaveMapper;
import com.marshal.halcyon.workflow.leavebill.service.LeaveBillService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/2/8
 * @desc:
 */
@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    public static final String LeaveBillProcessDefinitionKey = "LeafBill";

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    IdentityService identityService;

    @Autowired
    TaskService taskService;

    @Autowired
    ActBizLeaveMapper actBizLeaveMapper;

    @Override
    public void createLeaveBillInstance(ActBizLeave actBizLeave) {
        actBizLeaveMapper.insertSelective(actBizLeave);


        Map<String, Object> variables = new HashMap<>();
        variables.put("leaveReason", actBizLeave.getLeaveReason());

        identityService.setAuthenticatedUserId("Marshal");
        runtimeService.startProcessInstanceByKey(LeaveBillProcessDefinitionKey, String.valueOf(actBizLeave.getId()), variables);

    }

    @Override
    public List<ActBizLeave> query(ActBizLeave condition) {
        return actBizLeaveMapper.select(condition);
    }

    @Override
    public List<ActBizLeave> submit(List<ActBizLeave> actBizLeaves) {
        actBizLeaves.forEach(item -> {
            if (item.getId() == null) {
                actBizLeaveMapper.insert(item);
            } else {
                actBizLeaveMapper.updateByPrimaryKey(item);
            }
        });
        return actBizLeaves;
    }

    @Override
    public List<ActBizLeave> remove(List<ActBizLeave> actBizLeaves) {
        actBizLeaves.forEach(item -> actBizLeaveMapper.deleteByPrimaryKey(item));
        return actBizLeaves;
    }

    @Override
    public ActBizLeave queryById(Long id) {
        return actBizLeaveMapper.selectByPrimaryKey(id);
    }
}

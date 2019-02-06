package com.marshal.halcyon.workflow.util;

import com.marshal.halcyon.hr.entity.HrEmployee;
import com.marshal.halcyon.hr.entity.HrPosition;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc: 工作流相关工具类
 */
public class ActivitiUtil {

    public static UserEntity toActivitiUser(HrEmployee emp) {
        UserEntityImpl entity = new UserEntityImpl();
        if (emp == null) {
            return entity;
        }
        entity.setId(emp.getEmployeeCode());
        String empName = emp.getName();
        entity.setFirstName(StringUtils.defaultIfEmpty(empName, "UNKNOWN"));
        entity.setLastName("");
        entity.setEmail(emp.getEmail());
        entity.setRevision(1);
        return entity;
    }

    public static GroupEntity toActivitiGroup(HrPosition position) {
        GroupEntityImpl groupEntity = new GroupEntityImpl();
        if (position == null) {
            return groupEntity;
        }
        groupEntity.setRevision(1);
        groupEntity.setId(position.getPositionCode());
        groupEntity.setName(position.getName());
        groupEntity.setType("assignment");
        return groupEntity;
    }

    public static List<Group> toActivitiGroups(List<String> groupIds) {
        return null;
    }


    /**
     * 返回第二个日期与第一个日期之间相差的秒数
     *
     * @return 可能为负数，表明第二个日期在第一个日期之前
     */
    public static Long secondsBetweenDate(Date firstDate, Date secondDate) {
        Long interval = (Long) ((secondDate.getTime() - firstDate.getTime()) / 1000);
        return interval;
    }
}

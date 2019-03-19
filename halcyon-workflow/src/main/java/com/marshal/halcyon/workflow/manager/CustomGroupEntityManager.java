package com.marshal.halcyon.workflow.manager;


import com.marshal.halcyon.base.hr.entity.HrPosition;
import com.marshal.halcyon.base.hr.mapper.HrPositionMapper;
import com.marshal.halcyon.workflow.util.ActivitiUtil;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisGroupDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc:
 */
@Component
public class CustomGroupEntityManager implements GroupEntityManager, Session {

    @Autowired
    HrPositionMapper positionMapper;

    public CustomGroupEntityManager() {
    }

    @Override
    public Group createNewGroup(String s) {
        return null;
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        return null;
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl groupQuery, Page page) {
        return null;
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl groupQuery) {
        return 0;
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<HrPosition> positions = positionMapper.getPositionByEmployeeCode(userId);
        List<Group> gs = new ArrayList<>();
        for (HrPosition position : positions) {
            gs.add(ActivitiUtil.toActivitiGroup(position));
        }
        return gs;
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> map, int i, int i1) {
        return null;
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewGroup(Group group) {
        return false;
    }

    @Override
    public GroupEntity create() {
        return null;
    }

    @Override
    public GroupEntity findById(String entityId) {
        GroupEntity groupEntity = new GroupEntityImpl();
        HrPosition condition = new HrPosition();
        condition.setPositionCode(entityId);
        HrPosition position = positionMapper.selectOne(condition);
        groupEntity = ActivitiUtil.toActivitiGroup(position);

        return groupEntity;
    }

    @Override
    public void insert(GroupEntity entity) {

    }

    @Override
    public void insert(GroupEntity entity, boolean b) {

    }

    @Override
    public GroupEntity update(GroupEntity entity) {
        return null;
    }

    @Override
    public GroupEntity update(GroupEntity entity, boolean b) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(GroupEntity entity) {

    }

    @Override
    public void delete(GroupEntity entity, boolean b) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }
}

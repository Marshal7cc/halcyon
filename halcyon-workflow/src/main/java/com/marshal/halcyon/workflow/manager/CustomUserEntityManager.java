package com.marshal.halcyon.workflow.manager;

import com.marshal.halcyon.hr.entity.HrEmployee;
import com.marshal.halcyon.hr.entity.HrPosition;
import com.marshal.halcyon.hr.mapper.HrEmployeeMapper;
import com.marshal.halcyon.hr.mapper.HrPositionMapper;
import com.marshal.halcyon.workflow.util.ActivitiUtil;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisUserDataManager;
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
public class CustomUserEntityManager implements UserEntityManager, Session {

    @Override
    public void close() {

    }

    @Override
    public void flush() {

    }

    @Autowired
    HrEmployeeMapper employeeMapper;

    @Autowired
    HrPositionMapper positionMapper;

    public Map<String, UserEntity> userCache = new HashMap<>();

    public CustomUserEntityManager() {
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl userQuery, Page page) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity, boolean b) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void delete(UserEntity entity, boolean b) {

    }

    @Override
    public void insert(UserEntity entity, boolean b) {

    }

    @Override
    public void insert(UserEntity entity) {

    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl userQuery) {
        return 0;
    }

    @Override
    public UserQuery createNewUserQuery() {
        return null;
    }

    @Override
    public Boolean checkPassword(String s, String s1) {
        return null;
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> map, int i, int i1) {
        return null;
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewUser(User user) {
        return false;
    }

    @Override
    public Picture getUserPicture(String s) {
        return null;
    }

    @Override
    public void setUserPicture(String s, Picture picture) {

    }

    @Override
    public void deletePicture(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User createNewUser(String s) {
        return null;
    }

    @Override
    public UserEntity create() {
        return null;
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
    public UserEntity findById(String entityId) {
        UserEntity userEntity = userCache.get(entityId);
        if (userEntity == null) {
            HrEmployee condition = new HrEmployee();
            condition.setEmployeeCode(entityId);
            HrEmployee employee = employeeMapper.selectOne(condition);
            userEntity = ActivitiUtil.toActivitiUser(employee);
            userCache.put(entityId, userEntity);
        }
        return userEntity;
    }
}

package com.marshal.halcyon.base.account.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_role_function")
public class SysRoleFunction {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 功能id
     */
    @Id
    @Column(name = "function_id")
    private Long functionId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "object_version")
    private Long objectVersion;

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取功能id
     *
     * @return function_id - 功能id
     */
    public Long getFunctionId() {
        return functionId;
    }

    /**
     * 设置功能id
     *
     * @param functionId 功能id
     */
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return object_version
     */
    public Long getObjectVersion() {
        return objectVersion;
    }

    /**
     * @param objectVersion
     */
    public void setObjectVersion(Long objectVersion) {
        this.objectVersion = objectVersion;
    }
}
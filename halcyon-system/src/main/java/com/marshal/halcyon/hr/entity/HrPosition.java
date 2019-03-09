package com.marshal.halcyon.hr.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "hr_position")
public class HrPosition extends BaseEntity {
    @Id
    @Column(name = "POSITION_ID")
    private Long positionId;

    /**
     * 组织ID
     */
    @Column(name = "UNIT_ID")
    private Long unitId;

    /**
     * 岗位编码
     */
    @Column(name = "POSITION_CODE")
    private String positionCode;

    @Column(name = "NAME")
    private String name;

    /**
     * 岗位描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 父岗位ID
     */
    @Column(name = "PARENT_POSITION_ID")
    private Long parentPositionId;

    @Transient
    private String parentPositionName;

    @Transient
    private String unitName;

    /**
     * 启用状态
     */
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;

    /**
     * @return POSITION_ID
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * @param positionId
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取组织ID
     *
     * @return UNIT_ID - 组织ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 设置组织ID
     *
     * @param unitId 组织ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取岗位编码
     *
     * @return POSITION_CODE - 岗位编码
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * 设置岗位编码
     *
     * @param positionCode 岗位编码
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取岗位描述
     *
     * @return DESCRIPTION - 岗位描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置岗位描述
     *
     * @param description 岗位描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取父岗位ID
     *
     * @return PARENT_POSITION_ID - 父岗位ID
     */
    public Long getParentPositionId() {
        return parentPositionId;
    }

    /**
     * 设置父岗位ID
     *
     * @param parentPositionId 父岗位ID
     */
    public void setParentPositionId(Long parentPositionId) {
        this.parentPositionId = parentPositionId;
    }

    /**
     * 获取启用状态
     *
     * @return ENABLED_FLAG - 启用状态
     */
    public String getEnabledFlag() {
        return enabledFlag;
    }

    /**
     * 设置启用状态
     *
     * @param enabledFlag 启用状态
     */
    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getParentPositionName() {
        return parentPositionName;
    }

    public void setParentPositionName(String parentPositionName) {
        this.parentPositionName = parentPositionName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
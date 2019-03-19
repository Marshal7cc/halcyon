package com.marshal.halcyon.base.hr.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "hr_unit")
public class HrUnit extends BaseEntity {
    @Id
    @Column(name = "UNIT_ID")
    private Long unitId;

    /**
     * 父组织
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    @Transient
    private String parentUnitCode;

    @Transient
    private String parentUnitName;

    /**
     * 组织编码
     */
    @Column(name = "UNIT_CODE")
    private String unitCode;

    @Column(name = "NAME")
    private String name;

    /**
     * 组织描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 组织管理岗位
     */
    @Column(name = "MANAGER_POSITION")
    private Long managerPosition;

    @Transient
    private String managerPositionName;

    /**
     * 公司ID
     */
    @Column(name = "COMPANY_ID")
    private Long companyId;

    /**
     * 启用状态
     */
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;

    /**
     * 组织类别
     */
    @Column(name = "UNIT_CATEGORY")
    private String unitCategory;

    /**
     * 组织分类
     */
    @Column(name = "UNIT_TYPE")
    private String unitType;

    /**
     * @return UNIT_ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取父组织
     *
     * @return PARENT_ID - 父组织
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父组织
     *
     * @param parentId 父组织
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取组织编码
     *
     * @return UNIT_CODE - 组织编码
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * 设置组织编码
     *
     * @param unitCode 组织编码
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
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
     * 获取组织描述
     *
     * @return DESCRIPTION - 组织描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置组织描述
     *
     * @param description 组织描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取组织管理岗位
     *
     * @return MANAGER_POSITION - 组织管理岗位
     */
    public Long getManagerPosition() {
        return managerPosition;
    }

    /**
     * 设置组织管理岗位
     *
     * @param managerPosition 组织管理岗位
     */
    public void setManagerPosition(Long managerPosition) {
        this.managerPosition = managerPosition;
    }

    /**
     * 获取公司ID
     *
     * @return COMPANY_ID - 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    /**
     * 获取组织类别
     *
     * @return UNIT_CATEGORY - 组织类别
     */
    public String getUnitCategory() {
        return unitCategory;
    }

    /**
     * 设置组织类别
     *
     * @param unitCategory 组织类别
     */
    public void setUnitCategory(String unitCategory) {
        this.unitCategory = unitCategory;
    }

    /**
     * 获取组织分类
     *
     * @return UNIT_TYPE - 组织分类
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * 设置组织分类
     *
     * @param unitType 组织分类
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getParentUnitCode() {
        return parentUnitCode;
    }

    public void setParentUnitCode(String parentUnitCode) {
        this.parentUnitCode = parentUnitCode;
    }

    public String getParentUnitName() {
        return parentUnitName;
    }

    public void setParentUnitName(String parentUnitName) {
        this.parentUnitName = parentUnitName;
    }

    public String getManagerPositionName() {
        return managerPositionName;
    }

    public void setManagerPositionName(String managerPositionName) {
        this.managerPositionName = managerPositionName;
    }
}
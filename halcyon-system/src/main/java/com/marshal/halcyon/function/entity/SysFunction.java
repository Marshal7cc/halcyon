package com.marshal.halcyon.function.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Table(name = "sys_function")
public class SysFunction extends BaseEntity {
    @Id
    @Column(name = "FUNCTION_ID")
    @GeneratedValue(generator = "JDBC")
    private Long functionId;

    /**
     * 模块编码
     */
    @Column(name = "MODULE_CODE")
    private String moduleCode;

    /**
     * 功能图标
     */
    @Column(name = "FUNCTION_ICON")
    private String functionIcon;

    /**
     * 功能编码
     */
    @Column(name = "FUNCTION_CODE")
    private String functionCode;

    /**
     * 功能名称
     */
    @Column(name = "FUNCTION_NAME")
    private String functionName;

    /**
     * 描述
     */
    @Column(name = "FUNCTION_DESCRIPTION")
    private String functionDescription;

    /**
     * 功能入口
     */
    @Column(name = "RESOURCE_ID")
    private Long resourceId;

    /**
     * 父功能
     */
    @Column(name = "PARENT_FUNCTION_ID")
    private Long parentFunctionId;

    /**
     * 是否启用
     */
    @Column(name = "ENABLE_FLAG")
    private String enableFlag;

    /**
     * 排序号
     */
    @Column(name = "FUNCTION_SEQUENCE")
    private BigDecimal functionSequence;

    @Transient
    private String url;

    @Transient
    private List<SysFunction> childFunctions;

    @Transient
    private String parentFunctionName;

    @Transient
    private String resourceName;

    /**
     * @return FUNCTION_ID
     */
    public Long getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId
     */
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    /**
     * 获取模块编码
     *
     * @return MODULE_CODE - 模块编码
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * 设置模块编码
     *
     * @param moduleCode 模块编码
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * 获取功能图标
     *
     * @return FUNCTION_ICON - 功能图标
     */
    public String getFunctionIcon() {
        return functionIcon;
    }

    /**
     * 设置功能图标
     *
     * @param functionIcon 功能图标
     */
    public void setFunctionIcon(String functionIcon) {
        this.functionIcon = functionIcon;
    }

    /**
     * 获取功能编码
     *
     * @return FUNCTION_CODE - 功能编码
     */
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * 设置功能编码
     *
     * @param functionCode 功能编码
     */
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * 获取功能名称
     *
     * @return FUNCTION_NAME - 功能名称
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * 设置功能名称
     *
     * @param functionName 功能名称
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * 获取描述
     *
     * @return FUNCTION_DESCRIPTION - 描述
     */
    public String getFunctionDescription() {
        return functionDescription;
    }

    /**
     * 设置描述
     *
     * @param functionDescription 描述
     */
    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    /**
     * 获取功能入口
     *
     * @return RESOURCE_ID - 功能入口
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置功能入口
     *
     * @param resourceId 功能入口
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取父功能
     *
     * @return PARENT_FUNCTION_ID - 父功能
     */
    public Long getParentFunctionId() {
        return parentFunctionId;
    }

    /**
     * 设置父功能
     *
     * @param parentFunctionId 父功能
     */
    public void setParentFunctionId(Long parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    /**
     * 获取是否启用
     *
     * @return ENABLE_FLAG - 是否启用
     */
    public String getEnableFlag() {
        return enableFlag;
    }

    /**
     * 设置是否启用
     *
     * @param enableFlag 是否启用
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    /**
     * 获取排序号
     *
     * @return FUNCTION_SEQUENCE - 排序号
     */
    public BigDecimal getFunctionSequence() {
        return functionSequence;
    }

    /**
     * 设置排序号
     *
     * @param functionSequence 排序号
     */
    public void setFunctionSequence(BigDecimal functionSequence) {
        this.functionSequence = functionSequence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SysFunction> getChildFunctions() {
        return childFunctions;
    }

    public void setChildFunctions(List<SysFunction> childFunctions) {
        this.childFunctions = childFunctions;
    }

    public String getParentFunctionName() {
        return parentFunctionName;
    }

    public void setParentFunctionName(String parentFunctionName) {
        this.parentFunctionName = parentFunctionName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
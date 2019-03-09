package com.marshal.halcyon.function.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource extends BaseEntity {
    @Id
    @Column(name = "RESOURCE_ID")
    @GeneratedValue(generator = "JDBC")
    private Long resourceId;

    /**
     * URL
     */
    @Column(name = "URL")
    private String url;

    /**
     * 资源类型
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 资源名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 是否需要登录
     */
    @Column(name = "LOGIN_REQUIRE")
    private String loginRequire;

    /**
     * 是否权限校验
     */
    @Column(name = "ACCESS_CHECK")
    private String accessCheck;

    /**
     * @return RESOURCE_ID
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取URL
     *
     * @return URL - URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取资源类型
     *
     * @return TYPE - 资源类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置资源类型
     *
     * @param type 资源类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取资源名称
     *
     * @return NAME - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否需要登录
     *
     * @return LOGIN_REQUIRE - 是否需要登录
     */
    public String getLoginRequire() {
        return loginRequire;
    }

    /**
     * 设置是否需要登录
     *
     * @param loginRequire 是否需要登录
     */
    public void setLoginRequire(String loginRequire) {
        this.loginRequire = loginRequire;
    }

    /**
     * 获取是否权限校验
     *
     * @return ACCESS_CHECK - 是否权限校验
     */
    public String getAccessCheck() {
        return accessCheck;
    }

    /**
     * 设置是否权限校验
     *
     * @param accessCheck 是否权限校验
     */
    public void setAccessCheck(String accessCheck) {
        this.accessCheck = accessCheck;
    }
}
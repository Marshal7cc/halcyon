package com.marshal.halcyon.base.ueditor.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_config")
public class SysConfig extends BaseEntity {
    @Id
    @Column(name = "CONFIG_ID")
    private Long configId;

    @Column(name = "CONFIG_CODE")
    private String configCode;

    @Column(name = "CONFIG_KEY")
    private String configKey;

    @Column(name = "CONFIG_VALUE")
    private String configValue;

    @Column(name = "CONFIG_CATEGORY")
    private String configCategory;

    /**
     * @return CONFIG_ID
     */
    public Long getConfigId() {
        return configId;
    }

    /**
     * @param configId
     */
    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    /**
     * @return CONFIG_CODE
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * @param configCode
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    /**
     * @return CONFIG_KEY
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * @param configKey
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * @return CONFIG_VALUE
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * @param configValue
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * @return CONFIG_CATEGORY
     */
    public String getConfigCategory() {
        return configCategory;
    }

    /**
     * @param configCategory
     */
    public void setConfigCategory(String configCategory) {
        this.configCategory = configCategory;
    }
}
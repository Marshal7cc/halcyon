package com.marshal.halcyon.core.entity;

import tk.mybatis.mapper.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * @auth: Marshal
 * @date: 2019/3/9
 * @desc: 基础实体对象，存储公用属性
 */
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    /**
     * 乐观锁关键字
     */
    @Version
    private Long objectVersion;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Long objectVersion) {
        this.objectVersion = objectVersion;
    }
}

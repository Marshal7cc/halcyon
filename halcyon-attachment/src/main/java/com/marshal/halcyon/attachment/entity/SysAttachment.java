package com.marshal.halcyon.attachment.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.ibatis.annotations.SelectKey;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.halcyon.core.entity.BaseEntity;

@Table(name = "sys_attachment")
public class SysAttachment extends BaseEntity {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long attachmentId;

    private Long categoryId; //分类ID

    @Length(max = 40)
    private String name; //附件名称

    @Length(max = 30)
    private String sourceType; //对应业务类型

    @Length(max = 40)
    private String sourceKey; //业务主键


    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getSourceKey() {
        return sourceKey;
    }

}

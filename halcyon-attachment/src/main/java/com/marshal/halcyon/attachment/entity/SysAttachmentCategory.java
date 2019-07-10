package com.marshal.halcyon.attachment.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.halcyon.core.entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "sys_attachment_category")
public class SysAttachmentCategory extends BaseEntity {

    @Id
    private Long categoryId;

    @NotEmpty
    @Length(max = 40)
    private String categoryName;

    @Length(max = 1)
    private String leafFlag;

    @Length(max = 240)
    private String description;

    @Length(max = 1)
    private String enabledFlag;

    private Long parentCategoryId;

    @Length(max = 200)
    private String path; //层级路径

    @Length(max = 100)
    private String sourceType;

    @Length(max = 300)
    private String allowedFileType;

    private Long allowedFileSize;

    @Length(max = 1)
    private String isUnique;

    @Length(max = 255)
    private String categoryPath;


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setAllowedFileType(String allowedFileType) {
        this.allowedFileType = allowedFileType;
    }

    public String getAllowedFileType() {
        return allowedFileType;
    }

    public void setAllowedFileSize(Long allowedFileSize) {
        this.allowedFileSize = allowedFileSize;
    }

    public Long getAllowedFileSize() {
        return allowedFileSize;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

}

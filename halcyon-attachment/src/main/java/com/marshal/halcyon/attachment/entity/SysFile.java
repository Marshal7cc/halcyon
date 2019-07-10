package com.marshal.halcyon.attachment.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.halcyon.core.entity.BaseEntity;

import java.util.Date;

@Table(name = "sys_file")
public class SysFile extends BaseEntity {

    @Id
    private Long fileId;

    private Long attachmentId; //附件ID

    @Length(max = 255)
    private String fileName; //文件名

    @Length(max = 255)
    private String filePath; //文件路径

    private Long fileSize; //文件大小

    @Length(max = 240)
    private String fileType; //文件类型

    private Date uploadTime; //上传时间


    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

}

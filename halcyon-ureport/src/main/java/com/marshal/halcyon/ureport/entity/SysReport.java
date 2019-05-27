package com.marshal.halcyon.ureport.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.halcyon.core.entity.BaseEntity;

@Table(name = "sys_report")
public class SysReport extends BaseEntity {

    @Id
    private Long reportId;

    @Length(max = 100)
    private String reportCode;

    @Length(max = 100)
    private String reportName;

    @Length(max = 100)
    private String reportFileName;

    @Length(max = 255)
    private String description;


    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

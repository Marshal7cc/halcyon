package com.marshal.halcyon.hr.entity;

import com.marshal.halcyon.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "hr_employee")
public class HrEmployee extends BaseEntity {
    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    /**
     * 员工编码
     */
    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;

    /**
     * 员工姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 出生日期
     */
    @Column(name = "BORN_DATE")
    private Date bornDate;

    /**
     * 电子邮件
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 移动电话
     */
    @Column(name = "MOBIL")
    private String mobil;

    /**
     * 入职日期
     */
    @Column(name = "JOIN_DATE")
    private Date joinDate;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * ID
     */
    @Column(name = "CERTIFICATE_ID")
    private String certificateId;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 启用状态
     */
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;

    /**
     * 证件类型
     */
    @Column(name = "CERTIFICATE_TYPE")
    private String certificateType;

    @Column(name = "POSITION_ID")
    private Long positionId;

    @Transient
    private String positionName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * @return EMPLOYEE_ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 获取员工编码
     *
     * @return EMPLOYEE_CODE - 员工编码
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * 设置员工编码
     *
     * @param employeeCode 员工编码
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * 获取员工姓名
     *
     * @return NAME - 员工姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置员工姓名
     *
     * @param name 员工姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取出生日期
     *
     * @return BORN_DATE - 出生日期
     */
    public Date getBornDate() {
        return bornDate;
    }

    /**
     * 设置出生日期
     *
     * @param bornDate 出生日期
     */
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    /**
     * 获取电子邮件
     *
     * @return EMAIL - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取移动电话
     *
     * @return MOBIL - 移动电话
     */
    public String getMobil() {
        return mobil;
    }

    /**
     * 设置移动电话
     *
     * @param mobil 移动电话
     */
    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    /**
     * 获取入职日期
     *
     * @return JOIN_DATE - 入职日期
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * 设置入职日期
     *
     * @param joinDate 入职日期
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * 获取性别
     *
     * @return GENDER - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取ID
     *
     * @return CERTIFICATE_ID - ID
     */
    public String getCertificateId() {
        return certificateId;
    }

    /**
     * 设置ID
     *
     * @param certificateId ID
     */
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 获取证件类型
     *
     * @return CERTIFICATE_TYPE - 证件类型
     */
    public String getCertificateType() {
        return certificateType;
    }

    /**
     * 设置证件类型
     *
     * @param certificateType 证件类型
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
package com.marshal.halcyon.hr.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "hr_company")
public class HrCompany {
    @Id
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    /**
     * 公司类型
     */
    @Column(name = "COMPANY_TYPE")
    private String companyType;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 公司级别
     */
    @Column(name = "COMPANY_LEVEL_ID")
    private Long companyLevelId;

    /**
     * 母公司
     */
    @Column(name = "PARENT_COMPANY_ID")
    private Long parentCompanyId;

    /**
     * 主岗位
     */
    @Column(name = "CHIEF_POSITION_ID")
    private Long chiefPositionId;

    /**
     * 有效时间开始
     */
    @Column(name = "START_DATE_ACTIVE")
    private Date startDateActive;

    /**
     * 有效时间截止
     */
    @Column(name = "END_DATE_ACTIVE")
    private Date endDateActive;

    @Column(name = "COMPANY_SHORT_NAME")
    private String companyShortName;

    @Column(name = "COMPANY_FULL_NAME")
    private String companyFullName;

    /**
     * 邮编
     */
    @Column(name = "ZIPCODE")
    private String zipcode;

    /**
     * 传真
     */
    @Column(name = "FAX")
    private String fax;

    /**
     * 联系电话
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * 联系人
     */
    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    /**
     * @return COMPANY_ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * @return COMPANY_CODE
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * @param companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * 获取公司类型
     *
     * @return COMPANY_TYPE - 公司类型
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置公司类型
     *
     * @param companyType 公司类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取地址
     *
     * @return ADDRESS - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取公司级别
     *
     * @return COMPANY_LEVEL_ID - 公司级别
     */
    public Long getCompanyLevelId() {
        return companyLevelId;
    }

    /**
     * 设置公司级别
     *
     * @param companyLevelId 公司级别
     */
    public void setCompanyLevelId(Long companyLevelId) {
        this.companyLevelId = companyLevelId;
    }

    /**
     * 获取母公司
     *
     * @return PARENT_COMPANY_ID - 母公司
     */
    public Long getParentCompanyId() {
        return parentCompanyId;
    }

    /**
     * 设置母公司
     *
     * @param parentCompanyId 母公司
     */
    public void setParentCompanyId(Long parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    /**
     * 获取主岗位
     *
     * @return CHIEF_POSITION_ID - 主岗位
     */
    public Long getChiefPositionId() {
        return chiefPositionId;
    }

    /**
     * 设置主岗位
     *
     * @param chiefPositionId 主岗位
     */
    public void setChiefPositionId(Long chiefPositionId) {
        this.chiefPositionId = chiefPositionId;
    }

    /**
     * 获取有效时间开始
     *
     * @return START_DATE_ACTIVE - 有效时间开始
     */
    public Date getStartDateActive() {
        return startDateActive;
    }

    /**
     * 设置有效时间开始
     *
     * @param startDateActive 有效时间开始
     */
    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    /**
     * 获取有效时间截止
     *
     * @return END_DATE_ACTIVE - 有效时间截止
     */
    public Date getEndDateActive() {
        return endDateActive;
    }

    /**
     * 设置有效时间截止
     *
     * @param endDateActive 有效时间截止
     */
    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    /**
     * @return COMPANY_SHORT_NAME
     */
    public String getCompanyShortName() {
        return companyShortName;
    }

    /**
     * @param companyShortName
     */
    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    /**
     * @return COMPANY_FULL_NAME
     */
    public String getCompanyFullName() {
        return companyFullName;
    }

    /**
     * @param companyFullName
     */
    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    /**
     * 获取邮编
     *
     * @return ZIPCODE - 邮编
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * 设置邮编
     *
     * @param zipcode 邮编
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 获取传真
     *
     * @return FAX - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取联系电话
     *
     * @return PHONE - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系人
     *
     * @return CONTACT_PERSON - 联系人
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 设置联系人
     *
     * @param contactPerson 联系人
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
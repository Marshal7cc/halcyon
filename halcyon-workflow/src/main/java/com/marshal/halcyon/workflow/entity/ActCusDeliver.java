package com.marshal.halcyon.workflow.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

import com.marshal.halcyon.core.entity.BaseEntity;

import java.util.Date;

@Table(name = "act_cus_deliver")
public class ActCusDeliver extends BaseEntity {

    @Id
    private Long id;

    @Length(max = 30)
    private String employeeCode; //员工代码

    @Length(max = 30)
    private String deliverCode; //转交人员工代码

    private Date deliverStartDate; //有效期从

    private Date deliverEndDate; //有效期至


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverStartDate(Date deliverStartDate) {
        this.deliverStartDate = deliverStartDate;
    }

    public Date getDeliverStartDate() {
        return deliverStartDate;
    }

    public void setDeliverEndDate(Date deliverEndDate) {
        this.deliverEndDate = deliverEndDate;
    }

    public Date getDeliverEndDate() {
        return deliverEndDate;
    }

}

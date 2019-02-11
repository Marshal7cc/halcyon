package com.marshal.halcyon.leavebill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marshal.halcyon.core.constants.BaseConstants;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "act_biz_leave")
public class ActBizLeave {
    @Id
    @Column(name = "ID")
    @KeySql(useGeneratedKeys = true, sql = "SELECT LAST_INSERT_ID()", order = ORDER.AFTER)
    private Long id;

    /**
     * 用户编码
     */
    @Column(name = "USER_CODE")
    private String userCode;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Column(name = "START_DATE")
    private Date startDate;

    /**
     * 请假理由
     */
    @Column(name = "LEAVE_REASON")
    private String leaveReason;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户编码
     *
     * @return USER_CODE - 用户编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户编码
     *
     * @param userCode 用户编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return START_DATE
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取请假理由
     *
     * @return LEAVE_REASON - 请假理由
     */
    public String getLeaveReason() {
        return leaveReason;
    }

    /**
     * 设置请假理由
     *
     * @param leaveReason 请假理由
     */
    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}
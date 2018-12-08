package com.marshal.mcap.system.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_request_info")
public class SysRequestInfo {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(generator = "JDBC")
    private Long requestId;

    @Column(name = "request_time")
    private Date requestTime;

    private String url;

    private String os;

    private String browser;

    @Column(name = "remote_addr")
    private String remoteAddr;

    private Long duration;

    @Column(name = "is_success")
    private String isSuccess;

    /**
     * @return request_id
     */
    public Long getRequestId() {
        return requestId;
    }

    /**
     * @param requestId
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /**
     * @return request_time
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * @param requestTime
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * @return remote_addr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * @param remoteAddr
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @return duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * @param duration
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * @return is_success
     */
    public String getIsSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     */
    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}
package com.marshal.halcyon.es.dto;

import com.marshal.halcyon.core.entity.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "halcyon-sys", type = "sys_request", shards = 5, replicas = 1)
public class SysRequest extends BaseEntity {

    @Id
    @Field(type = FieldType.Long, store = true)
    private Long requestId;

    @Field(type = FieldType.Date, store = true)
    private Date requestTime;

    @Field(type = FieldType.Text, store = true, analyzer = "standard")
    private String url;

    @Field(type = FieldType.Text, store = true, analyzer = "standard")
    private String os;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String browser;

    @Field(type = FieldType.Text, store = true, analyzer = "standard")
    private String remoteAddr;

    @Field(type = FieldType.Long, store = true)
    private Long duration;

    @Field(type = FieldType.Boolean, store = true  )
    private String isSuccess;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}

package com.marshal.halcyon.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 标准返回数据格式
 */
public class ResponseData implements Serializable {
    /**
     * 记录总数for page
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long total;

    /**
     * 返回数据list
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List rows;

    /**
     * 状态,默认为true
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean success = true;

    /**
     * 提示信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ResponseData() {
    }

    /**
     * 传入list，设置记录总数和数据列表信息
     *
     * @param rows
     */
    public ResponseData(List rows) {
        try {
            Page<?> page = (Page<?>) rows;
            this.rows = rows;
            this.total = page.getTotal();
        } catch (Exception e) {
            this.rows = rows;
            this.total = rows.size();
        }
    }

    public ResponseData(List rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public ResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

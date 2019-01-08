package com.marshal.halcyon.quartz.entity;

import java.io.Serializable;

/**
 * @auth: Marshal
 * @date: 2018/11/4
 * @desc: job参数实体类
 */
public class JobData implements Serializable {

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

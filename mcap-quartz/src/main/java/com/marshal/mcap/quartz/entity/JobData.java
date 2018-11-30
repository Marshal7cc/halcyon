package com.marshal.mcap.quartz.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/4
 * Time: 14:15
 * Description:job参数
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

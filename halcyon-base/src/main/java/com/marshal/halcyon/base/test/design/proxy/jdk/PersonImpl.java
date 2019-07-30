package com.marshal.halcyon.base.test.design.proxy.jdk;

/**
 * @auth: Marshal
 * @date: 2019/6/5
 * @desc:
 */
public class PersonImpl implements Person {

    private String name;

    public PersonImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName() {
        System.out.println("111");
    }
}

package com.marshal.halcyon.base.test.proxy;

import java.lang.reflect.Proxy;

/**
 * @auth: Marshal
 * @date: 2019/6/5
 * @desc:
 */
public class Client {
    public static void main(String[] args) {

        Person person = new PersonImpl("Marshal");
        Person proxy = getProxy(person);
        proxy.getName();
        proxy.setName();
    }

    public static Person getProxy(Person person) {
        return (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new SelfInvocationHandler(person));
    }
}

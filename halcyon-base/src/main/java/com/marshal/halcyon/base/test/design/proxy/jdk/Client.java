package com.marshal.halcyon.base.test.design.proxy.jdk;

/**
 * @auth: Marshal
 * @date: 2019/6/5
 * @desc:
 */
public class Client {
    public static void main(String[] args) {

        Person person = new PersonImpl("Marshal");
        SelfInvocationHandler proxyProvider = new SelfInvocationHandler();
        Person proxy = (Person) proxyProvider.bind(person);
        proxy.getName();
        proxy.setName();
    }

}

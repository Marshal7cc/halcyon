package com.marshal.halcyon.base.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auth: Marshal
 * @date: 2019/6/5
 * @desc:
 */
public class SelfInvocationHandler implements InvocationHandler {

    private Person person;

    public SelfInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(person, args);
        } else if (method.getName().startsWith("set")) {
            throw new IllegalArgumentException();
        } else {
            return method.invoke(person, args);
        }
    }
}

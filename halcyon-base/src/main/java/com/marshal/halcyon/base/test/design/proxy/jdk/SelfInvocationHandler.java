package com.marshal.halcyon.base.test.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auth: Marshal
 * @date: 2019/6/5
 * @desc:
 */
public class SelfInvocationHandler implements InvocationHandler {

    private Object target;

    /**
     * 可以写在内部/或写在外部
     *
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(target, args);
        } else if (method.getName().startsWith("set")) {
            throw new UnsupportedOperationException();
        } else {
            return method.invoke(target, args);
        }
    }
}

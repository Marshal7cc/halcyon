package com.marshal.halcyon.base.test.design.proxy.cglib;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        // 生成 Cglib 代理类
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        // 调用相关方法
        engineerProxy.eat();
        engineerProxy.work();
    }
}

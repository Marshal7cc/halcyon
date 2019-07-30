package com.marshal.halcyon.base.test.design.singleton;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc: 单例模式样例
 * <p>
 * 1.构造方法私有
 * 2.持有自己的引用
 * 3.提供一个公有方法创建实例
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {

    }

    //双重检查
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

package com.marshal.halcyon.base.test.concurrent;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class ShutdownHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        }));
    }
}

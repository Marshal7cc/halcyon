package com.marshal.halcyon.base.test.design.builder;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        DataSourceConfigBuilder builder = new DataSourceConfigBuilder();
        builder.host("localhost").port("3306").username("Marshak").password("pwd").build();
    }
}

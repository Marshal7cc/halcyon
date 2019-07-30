package com.marshal.halcyon.base.test.design.factorymethod;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        //关于如何获取factory，可以根据产品标识注册到map里
        ProductFactory productFactory = new ProductAFactory();
        productFactory.getProduct();

        productFactory = new ProductBFactory();
        productFactory.getProduct();
    }
}

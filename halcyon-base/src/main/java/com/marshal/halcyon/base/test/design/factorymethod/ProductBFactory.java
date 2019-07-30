package com.marshal.halcyon.base.test.design.factorymethod;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class ProductBFactory implements ProductFactory {

    public Product getProduct() {
        return new ProductB();
    }

}

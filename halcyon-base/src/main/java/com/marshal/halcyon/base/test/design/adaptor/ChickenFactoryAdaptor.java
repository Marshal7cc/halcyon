package com.marshal.halcyon.base.test.design.adaptor;

import com.marshal.halcyon.base.test.design.factorymethod.Product;
import com.marshal.halcyon.base.test.design.factorymethod.ProductFactory;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class ChickenFactoryAdaptor implements ProductFactory {

    private ChickenFactory chicken;

    @Override
    public Product getProduct() {
        return chicken.getChicken();
    }
}

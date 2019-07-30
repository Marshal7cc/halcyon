package com.marshal.halcyon.base.test.design.adaptor;

import com.marshal.halcyon.base.test.design.factorymethod.Product;
import com.marshal.halcyon.base.test.design.factorymethod.ProductA;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class ChickenFactory {

    //方法名与之前的工厂方法不兼容
    public Product getChicken() {
        System.out.println("chicken gaga");
        return new ProductA();
    }
}

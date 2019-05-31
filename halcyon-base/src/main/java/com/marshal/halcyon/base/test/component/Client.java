package com.marshal.halcyon.base.test.component;

/**
 * @auth: Marshal
 * @date: 2019/5/31
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        StrategyFactory strategyFactory = getStrategyFactory("BP");
        DocumentListGenerator generator = new DocumentListGenerator(strategyFactory.getStrategy());
        generator.generate(1L);
    }

    private static StrategyFactory getStrategyFactory(String type) {
        if ("BP".equals(type)) {
            return new BpStrategyFactory();
        } else {
            return null;
        }
    }
}

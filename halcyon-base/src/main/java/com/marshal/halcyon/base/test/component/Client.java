package com.marshal.halcyon.base.test.component;

/**
 * @auth: Marshal
 * @date: 2019/5/31
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        StrategyProvider provider = new StrategyProvider();
        GenerateStrategy strategy = provider.getStrategy("HLS_BP_MASTER");
        DocumentListGenerator generator = new DocumentListGenerator(strategy);
        generator.generate(1L);
    }

}

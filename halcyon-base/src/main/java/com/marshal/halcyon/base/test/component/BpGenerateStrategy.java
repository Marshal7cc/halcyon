package com.marshal.halcyon.base.test.component;

/**
 * @auth: Marshal
 * @date: 2019/6/1
 * @desc:
 */
public class BpGenerateStrategy implements GenerateStrategy {
    @Override
    public void generateDocumentList(Long documentId) {
        System.out.println("BP generate!");
    }
}

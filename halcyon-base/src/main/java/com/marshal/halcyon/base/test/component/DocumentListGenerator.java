package com.marshal.halcyon.base.test.component;


/**
 * @auth: Marshal
 * @date: 2019/5/31
 * @desc:
 */
public class DocumentListGenerator {

    private GenerateStrategy strategy;

    public DocumentListGenerator(GenerateStrategy strategy) {
        this.strategy = strategy;
    }

    void generate(Long documentId) {
        strategy.generateDocumentList(documentId);
    }

}

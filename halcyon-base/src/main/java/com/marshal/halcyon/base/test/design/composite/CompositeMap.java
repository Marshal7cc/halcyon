package com.marshal.halcyon.base.test.design.composite;

import java.util.HashMap;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class CompositeMap {

    private List<HashMap> child;

    public CompositeMap(List<HashMap> child) {
        this.child = child;
    }

    public List<HashMap> getChild() {
        return child;
    }

}

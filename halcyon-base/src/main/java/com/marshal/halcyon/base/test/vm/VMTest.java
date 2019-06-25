package com.marshal.halcyon.base.test.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/6/23
 * @desc:
 */
public class VMTest {

    //java堆溢出测试
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}

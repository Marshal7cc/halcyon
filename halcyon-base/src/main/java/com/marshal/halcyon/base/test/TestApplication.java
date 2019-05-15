package com.marshal.halcyon.base.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestApplication {
    public static void main(String[] args) {
        int result = Stream.of(1,2,3).reduce(0,(sum,i)->sum+i);
        System.out.println(result);

        String s = Stream.of("1","a","b").collect(Collectors.joining(",","[","]"));
        System.out.println(s);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.stream().mapToInt(a->a).sum();
    }
}

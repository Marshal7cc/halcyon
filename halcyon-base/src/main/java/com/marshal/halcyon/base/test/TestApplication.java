package com.marshal.halcyon.base.test;

import com.marshal.halcyon.base.account.entity.SysUser;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestApplication {
    public static void main(String[] args) {
        int result = Stream.of(1, 2, 3).reduce(0, (sum, i) -> sum + i);
        System.out.println(result);

        /**
         * lamda表达式
         */
        String s = Stream.of("1", "a", "b").collect(Collectors.joining(",", "[", "]"));
        System.out.println(s);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.stream().mapToInt(a -> a).sum();

        /**
         * comparable和comparator
         */
        List<String> asList = Arrays.asList("1", "3", "2");
        Collections.sort(asList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -1;
            }
        });


        System.out.println(asList);

        //SysUser类中LastLoginDate为final修饰
        SysUser user = new SysUser();
        Date currentDate = new Date();
        user.setLastLoginDate(currentDate);

        //修改其引用的内容,违背了类的设计
        currentDate.setTime(1999);

        String xmlStr = "<applications>"
                + "<versions__delta>1</versions__delta>"
                + "<apps__hashcode></apps__hashcode>"
                + "</applications>";

    }
}

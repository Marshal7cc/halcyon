package com.marshal.halcyon.security.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: Marshal
 * @date: 2018/11/29
 * @description:
 */
public class HalcyonTest {

    public static void main(String args[]){
        Logger logger = LoggerFactory.getLogger(HalcyonTest.class);
        String pwd = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(pwd);
        logger.info("【加密后的密码为：】" + encodedPassword);
    }
}

package com.marshal.halcyon.web;

import com.marshal.halcyon.security.properties.HalcyonSecurityProperties;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @class:exclude--SecurityAutoConfiguration
 * @desc: 排除activiti关于security的自动配置文件
 */
@ComponentScan(
        basePackages = {
                "com.marshal.halcyon.**.config",
                "com.marshal.halcyon.**.filter",
                "com.marshal.halcyon.**.controller",
                "com.marshal.halcyon.**.service",
                "com.marshal.halcyon.**.component",
                "com.marshal.halcyon.**.json",
                "org.activiti.rest.service.api",
                "com.marshal.halcyon.core.*"}
)
@MapperScan(
        basePackages =
                "com.marshal.halcyon.**.mapper"
)
@EnableJms
@EnableCaching
@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HalcyonApplication {
    public static void main(String[] args) {
        SpringApplication.run(HalcyonApplication.class, args);
        LoggerFactory.getLogger(HalcyonApplication.class).info(
                "《《《《《《 halcyon started up successfully at {} {} 》》》》》》", LocalDate.now(), LocalTime.now());
    }
}

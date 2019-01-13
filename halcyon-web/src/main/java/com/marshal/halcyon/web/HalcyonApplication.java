package com.marshal.halcyon.web;

import com.marshal.halcyon.security.properties.HalcyonSecurityProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalTime;

@ComponentScan(
        basePackages = {
                "com.marshal.halcyon.**.config",
                "com.marshal.halcyon.**.filter",
                "com.marshal.halcyon.**.controller",
                "com.marshal.halcyon.**.service",
                "com.marshal.halcyon.**.component",
                "com.marshal.halcyon.core.*",
                "com.marshal.halcyon.cache.impl"}
)
@tk.mybatis.spring.annotation.MapperScan(
        basePackages =
                "com.marshal.halcyon.**.mapper"
)
@EnableScheduling
@EnableConfigurationProperties(HalcyonSecurityProperties.class)
@SpringBootApplication
public class HalcyonApplication {
    public static void main(String[] args) {
        SpringApplication.run(HalcyonApplication.class, args);
        LoggerFactory.getLogger(HalcyonApplication.class).info(
                "《《《《《《 halcyon started up successfully at {} {} 》》》》》》", LocalDate.now(), LocalTime.now());
    }
}

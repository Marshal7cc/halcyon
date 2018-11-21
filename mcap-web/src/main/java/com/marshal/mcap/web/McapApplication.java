package com.marshal.mcap.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.marshal.mcap.**.controller", "com.marshal.mcap.**.service", "com.marshal.mcap.**.validator"})
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.marshal.mcap.**.mapper")
@SpringBootApplication
public class McapApplication {

    public static void main(String[] args) {
        SpringApplication.run(McapApplication.class, args);
    }
}

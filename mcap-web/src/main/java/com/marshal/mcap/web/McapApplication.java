package com.marshal.mcap.web;

import com.marshal.mcap.security.properties.McapSecurityProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import java.time.LocalDate;
import java.time.LocalTime;

@ComponentScan(
        basePackages = {
                "com.marshal.mcap.**.config",
                "com.marshal.mcap.**.controller",
                "com.marshal.mcap.**.service",
                "com.marshal.mcap.**.validator"}
)
@tk.mybatis.spring.annotation.MapperScan(
        basePackages =
                "com.marshal.mcap.**.mapper"
)
@EnableConfigurationProperties(McapSecurityProperties.class)
@SpringBootApplication
public class McapApplication {
    public static void main(String[] args) {
        SpringApplication.run(McapApplication.class, args);
        LoggerFactory.getLogger(McapApplication.class).info(
                "《《《《《《 MCAP started up successfully at {} {} 》》》》》》", LocalDate.now(), LocalTime.now());
    }
}

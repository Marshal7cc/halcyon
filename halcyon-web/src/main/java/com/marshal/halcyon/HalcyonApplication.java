package com.marshal.halcyon;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.time.LocalDate;
import java.time.LocalTime;

@MapperScan(
        basePackages =
                "com.marshal.halcyon.**.mapper"
)
@EnableJms
@EnableCaching
@EnableScheduling
//默认扫描主类所在包下的所有类,排除工作流关于security的自动配置文件
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HalcyonApplication {

    private static final Logger logger = LoggerFactory.getLogger(HalcyonApplication.class);

    public static void main(String[] args) {

        //解决Elasticsearch启动报错的问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        SpringApplication.run(HalcyonApplication.class, args);

        logger.info("halcyon start up successfully at {} {}", LocalDate.now(), LocalTime.now());

    }
}

package com.marshal.halcyon.ureport.config;

import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource("classpath:ureport-console-context.xml")
public class UReportConfig {

    /**
     * ureport核心servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean uReportServlet() {
        return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");
    }

}

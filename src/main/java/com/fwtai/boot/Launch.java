package com.fwtai.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.fwtai"})
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class Launch extends SpringBootServletInitializer{

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Launch.class,args);
        System.out.println("应用启动成功");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Launch.class);
    }
}
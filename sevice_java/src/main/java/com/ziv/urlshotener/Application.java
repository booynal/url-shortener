package com.ziv.urlshotener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.ziv.urlshotener")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

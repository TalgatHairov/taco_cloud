package com.example.tacocloud;

import com.example.tacocloud.controllers.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class TacoCloudApplication extends WebConfig {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }

}


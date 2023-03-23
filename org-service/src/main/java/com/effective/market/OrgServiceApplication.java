package com.effective.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrgServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrgServiceApplication.class, args);
    }

}

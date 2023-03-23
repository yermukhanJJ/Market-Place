package com.effective.market.marketeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MarketEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketEurekaServerApplication.class, args);
    }

}

package com.dmsoft.fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhixin.huang
 * @date 2019/04/08 17:39
 */
@EnableCaching
@SpringBootApplication
@EnableScheduling()
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.dmsoft"})
public class FireApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireApplication.class, args);

        //


        //


    }

}

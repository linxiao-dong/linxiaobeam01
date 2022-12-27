package com.gxa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.gxa.modules.sys.mapper"})
@EnableScheduling
public class SpringbootMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMpApplication.class, args);
    }

}

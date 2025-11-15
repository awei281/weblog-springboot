package com.wlog.wlogweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wlog.*.*"})
public class WlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WlogWebApplication.class, args);
    }

}

package com.tsystems.logistics.logistics_vp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class LogisticsVpApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsVpApplication.class, args);
    }
}

package com.awu.raft.services.regression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author LGP
 */
@SpringBootApplication(scanBasePackages = "com.awu.raft.services.regression")
@ComponentScan(basePackages = {"com.awu.raft.services.regression"})
public class RegressionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegressionApplication.class, args);
    }
}

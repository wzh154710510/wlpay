package org.wlpay.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan(basePackages={"org.wlpay"})
public class XxPayDubboWebAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(XxPayDubboWebAppliaction.class, args);
    }
}

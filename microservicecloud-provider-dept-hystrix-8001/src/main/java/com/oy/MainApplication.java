package com.oy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker // 对hystrixR熔断机制的支持
@EnableDiscoveryClient // 服务发现
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册进eureka服务中
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}

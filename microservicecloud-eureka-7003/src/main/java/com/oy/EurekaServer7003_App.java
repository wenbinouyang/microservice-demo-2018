package com.oy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Eureka Server 服务器端启动，接受其他微服务注册
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7003_App {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer7003_App.class, args);
	}
}

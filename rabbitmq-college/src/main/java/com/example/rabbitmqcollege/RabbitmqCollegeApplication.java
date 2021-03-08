package com.example.rabbitmqcollege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 流程：
 *
 * 发送消息MQ -> exchange -> RoutingKey -> Queue
 */
@SpringBootApplication
public class RabbitmqCollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqCollegeApplication.class, args);
	}

}

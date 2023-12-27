package com.zgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.zgm.config.DepartConfig;

@LoadBalancerClients(defaultConfiguration = DepartConfig.class)  //指定负载均衡的配置
@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}

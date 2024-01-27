package com.zgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.zgm.config.DepartConfig;

import reactor.core.publisher.Mono;

@LoadBalancerClients(defaultConfiguration = DepartConfig.class)  //指定负载均衡的配置
@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	//下面三个都是限流的API配置
	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
	}

	@Bean
	KeyResolver hstKeyResolver(){
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}

	@Bean
	KeyResolver pathKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}
}

package com.zgm.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class DepartConfig {
    

    @Bean
    public ReactorLoadBalancer<ServiceInstance> random(Environment e, LoadBalancerClientFactory factory){

        //获取负载均衡客户端名称,即提供者服务名称
        String name = e.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        //从所有provider实例中指令名称的实例列表中随机选择一个实例
        //参数一：获取指定名称的所有provider实例列表
        //参数二：指定要获取的provider服务名称
        return new RandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);

    }

}

package com.zgm.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 自定义网关过滤器 测试pre-filter和post-filter
 */
@Component
@Slf4j
public class OneGatewayFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange,chain) -> {
            //pre-filter
            long start = System.currentTimeMillis();
            log.info(config.getName()+ "-" + config.getValue() + "开始执行的时间："+start);
            // 因为后续要用到时间，所以将时间存入到上下文中
            exchange.getAttributes().put("startTime", start);
            return chain.filter(exchange).then(
                //post-filter
                Mono.fromRunnable(() -> {
                    long startTime = (long) exchange.getAttribute("startTime");
                    long endTime = System.currentTimeMillis();
                    log.info("使用时间："+(endTime-startTime));
                })
            );
        };
    }
    
}
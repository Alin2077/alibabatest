package com.zgm.factory;

import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 自定义网关过滤器
 */
@Component
public class AddHeaderGatewayFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange,chain) -> {

            //获取uri中的变量
            Map<String,String> uriTemplateVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);
            String bean = uriTemplateVariables.get("bean");
            String id = uriTemplateVariables.get("id");
            System.out.println("bean："+bean+",id："+id);

            ServerHttpRequest changedRequest = exchange.getRequest()
                                                        .mutate()
                                                        .header(config.getName(), config.getValue())
                                                        .build();

            ServerWebExchange changedExchange = exchange.mutate()
                                                        .request(changedRequest)
                                                        .build();
            
            return chain.filter(changedExchange);
        };
    }
    
}

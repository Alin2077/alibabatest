package com.zgm.factory;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;

@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Data
    public static class Config {

        private String token;
    }

    public List<String> shortcutFieldOrder(){
        return Collections.singletonList("token");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            MultiValueMap<String,String> queryParams = exchange.getRequest().getQueryParams();  //获取请求中的所有请求参数
            List<String> values = queryParams.get("token");
            if(values.contains(config.getToken())){
                return true;
            }
            return false;
        };
    }
    
}

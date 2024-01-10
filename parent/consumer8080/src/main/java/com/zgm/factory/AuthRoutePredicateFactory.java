package com.zgm.factory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;

@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {


    public AuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Data
    public static class Config {
        private String username;

        private String password;
        
    }

    public List<String> shortcutFieldOrder(){
        return Arrays.asList("username","password");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        
            return exchange -> {
                HttpHeaders headers = exchange.getRequest().getHeaders();  //获取到请求中所有的header
                List<String> pwds =  headers.get(config.getUsername()); //一个请求头可以包含多个值
                String[] values = pwds.get(0).split(",");
                for (String value : values) {
                    if(value.equalsIgnoreCase(config.getPassword())){
                        return true;
                    }
                }
                return false;
            };

    }
    
}

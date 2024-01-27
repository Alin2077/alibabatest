package com.zgm.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("baidu_route", ps -> ps.path("/bdd").uri("https://baidu.com")).build();
    }

    @Bean
    public RouteLocator cookiRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route("cookie_route", ps -> ps.cookie("city", "shanghai").uri("https://jd.com")).build();
    }

    @Bean
    public RouteLocator filterAddRequestHeaderRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route("addRequest_route",ps -> 
                                                    ps.path("/**").filters((fs -> fs.addRequestHeader("X-Request_coolor", "blue")
                                                                                                .addRequestHeader("X-Request_coolor", "red")))
                                                    .uri("http://localhost:8081")).build();
    }

    @Bean
    public RouteLocator filterAddRequestHeadersIfNotPresentRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                      .route("filterAddHeadersIfNotPresent_route",
                      ps -> ps.path("/**")
                              .filters(fs -> fs.addRequestHeadersIfNotPresent("X-Request-Color:blue","city:beijing"))
                              .uri("http://localhost:8081")).build();
    }   

    // 限流维度的实现类(只能有一个)
    @Bean
    KeyResolver userKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    // @Bean
    // KeyResolver hostKeyResolver(){
    //     return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    // }


}
 
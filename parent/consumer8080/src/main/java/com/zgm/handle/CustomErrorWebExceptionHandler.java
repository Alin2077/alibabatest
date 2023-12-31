package com.zgm.handle;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class CustomErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {


    public CustomErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                        WebProperties.Resources resources,
                                        ApplicationContext applicationContext,
                                        ServerCodecConfigurer serverCodecConfigurer){
            super(errorAttributes, resources, applicationContext);
            super.setMessageWriters(serverCodecConfigurer.getWriters());
            super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);

    }

    private  Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        //获取异常信息
        Map<String, Object> map = getErrorAttributes( request, ErrorAttributeOptions.defaults());
        //构建响应
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(map));
    }
    
}

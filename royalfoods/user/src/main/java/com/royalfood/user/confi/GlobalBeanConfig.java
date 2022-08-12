package com.royalfood.user.confi;

import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@Configuration
public class GlobalBeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

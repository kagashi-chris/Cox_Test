package com.zhen.coxTest.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient()
    {
        return WebClient.create("http://api.coxauto-interview.com/api");
    }
}

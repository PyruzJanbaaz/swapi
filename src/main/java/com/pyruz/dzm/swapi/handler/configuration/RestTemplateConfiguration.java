package com.pyruz.dzm.swapi.handler.configuration;

import com.pyruz.dzm.swapi.handler.exception.RestTemplateException;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient())));
        restTemplate.setErrorHandler(new RestTemplateException());
        return restTemplate;
    }

    @Bean
    public HttpClient httpClient() {
        return CachingHttpClientBuilder
                .create()
                .setCacheConfig(cacheConfig())
                .build();
    }

    @Bean
    public CacheConfig cacheConfig() {
        return CacheConfig
                .custom()
                .setMaxObjectSize(500000) // 500KB
                .setMaxCacheEntries(2000)
                .build();
    }
}
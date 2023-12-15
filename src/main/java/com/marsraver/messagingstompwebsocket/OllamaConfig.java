package com.marsraver.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnClass(name="org.springframework.ai.ollama.client.OllamaClient")
public class OllamaConfig {
    @Value("${spring.ai.ollama.baseUrl}")
    private String baseUrl;

    @Value("${spring.ai.ollama.model}")
    private String model;

//    @Bean
//    public OllamaClient ollamaClient() {
//        return new OllamaClient(baseUrl, model);
//    }
}

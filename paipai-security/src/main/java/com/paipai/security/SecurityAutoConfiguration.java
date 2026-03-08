package com.paipai.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.paipai.security" })
public class SecurityAutoConfiguration {

    public SecurityAutoConfiguration(ObjectMapper objectMapper) {
        // 设置序列化时忽略 null 变量
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }
}

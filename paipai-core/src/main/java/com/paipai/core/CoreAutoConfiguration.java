package com.paipai.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(AliyunProperties.class)
@ComponentScan(basePackages = { "com.paipai.core" })
public class CoreAutoConfiguration {

}

package com.paipai.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {

    private String endpoint;

    private String accesskeyId;

    private String accesskeySecret;

    private String ossBucketName;

    private String ossBucketUrl;

    private String ossRegion;

    private String ossFolder;
}

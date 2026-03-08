package com.paipai.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Data
@Profile("dev")
@Configuration
@PropertySource("classpath:sql-datasource.properties")
@ConfigurationProperties(prefix = "sql-datasource")
public class SqlDataSourceProperties {

    private String url;

    private String username;

    private String password;
}

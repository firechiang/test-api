package com.paipai.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class WebApiApplication {

    public static void main(String[] args) {

        Thread.currentThread().setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            log.error(t.threadId() + "=" + t.getName(), e);
        });
        SpringApplication.run(WebApiApplication.class, args);
    }
}

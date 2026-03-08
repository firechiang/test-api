package com.paipai.data;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;


@Configuration
@Import(SqlDataSourceProperties.class)
@MapperScan("com.paipai.data.mapper*")
public class DataAutoConfiguration {

    @Autowired(required = false)
    private SqlDataSourceProperties sqlDataSourceProperties;

    @Bean
    @Primary
    public MybatisProperties mp() {
        MybatisProperties mp = new MybatisProperties();
        mp.setTypeAliasesPackage("com.paipai.data.entity");
        mp.setMapperLocations(new String[]{"classpath*:/mapper/**/*Mapper.xml"});
        MybatisProperties.CoreConfiguration mc = new MybatisProperties.CoreConfiguration();
        mc.setMapUnderscoreToCamelCase(Boolean.TRUE);
        mc.setDefaultFetchSize(100);
        mc.setDefaultStatementTimeout(30);
        mc.setCacheEnabled(Boolean.FALSE);
        mc.setCallSettersOnNulls(Boolean.TRUE);
        mc.setJdbcTypeForNull(JdbcType.NULL);
        mp.setConfiguration(mc);
        return mp;
    }

    @Bean
    @Primary
    @Profile("dev")
    public HikariDataSource hds() {
        HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl(sqlDataSourceProperties.getUrl());
        hds.setUsername(sqlDataSourceProperties.getUsername());
        hds.setPassword(sqlDataSourceProperties.getPassword());
        hds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hds.setMinimumIdle(5);
        hds.setMaximumPoolSize(200);
        hds.setAutoCommit(true);
        hds.setIdleTimeout(30000L);
        hds.setPoolName("MySqlHikariPool");
        hds.setMaxLifetime(1800000L);
        hds.setConnectionTimeout(3000L);
        hds.setConnectionTestQuery("SELECT 1");
        return hds;
    }

}

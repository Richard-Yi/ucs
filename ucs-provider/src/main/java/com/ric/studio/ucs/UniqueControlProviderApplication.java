package com.ric.studio.ucs;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.ric.studio.ucs.service")
@MapperScan("com.ric.studio.ucs.mapper")
@EnableScheduling

@NacosPropertySource(dataId = "test.properties")
@NacosPropertySource(dataId = "jdbc.properties")
@NacosPropertySource(dataId = "example")
public class UniqueControlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniqueControlProviderApplication.class, args);
    }
}

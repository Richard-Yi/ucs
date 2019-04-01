package com.ric.studio.ucs.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/27
 */
@Configuration
public class ProviderDubboConfiguration {


    @Autowired
    private ProviderDubboProperties properties;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(properties.getApplication().getName());
//        applicationConfig.setQosEnable(false);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(properties.getRegistry().getProtocol());
        registryConfig.setAddress(properties.getRegistry().getAddress());
        registryConfig.setClient("curator");
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setId(properties.getProtocol().getId());
        protocolConfig.setName(properties.getProtocol().getName());
        protocolConfig.setHost(properties.getProtocol().getHost());
        protocolConfig.setPort(Integer.valueOf(properties.getProtocol().getPort()));
        return protocolConfig;
    }

}

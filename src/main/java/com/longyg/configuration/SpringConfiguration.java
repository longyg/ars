package com.longyg.configuration;

import com.longyg.service.NeService;
import com.longyg.service.impl.NeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringConfiguration{

    @Bean
    public NeService neService() {
        return new NeServiceImpl();
    }
}

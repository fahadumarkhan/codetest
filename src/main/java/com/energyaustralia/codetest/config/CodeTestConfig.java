package com.energyaustralia.codetest.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: fahadumarkhan
 * Date: 2019-04-16
 * Time: 09:28
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class CodeTestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, MappingJackson2HttpMessageConverter converter) {
        RestTemplate template = builder.build();
        template.getMessageConverters().add(0, converter);
        return template;
    }

}

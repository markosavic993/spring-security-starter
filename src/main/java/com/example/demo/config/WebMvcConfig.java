package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by msav on 12/29/2017.
 */
@Configuration
//this configuration replaces spring's servlet.xml
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean //Spring beans are objects that are instantiated and managed by Spring IoC container. They represent a recipe for creating actual instances
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

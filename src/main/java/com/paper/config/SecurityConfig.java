package com.paper.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //Security配置

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域
        http.csrf().disable();
        //拦截所有请求
        http.authorizeRequests().anyRequest().permitAll();
    }
}
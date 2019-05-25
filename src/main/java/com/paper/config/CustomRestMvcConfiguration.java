package com.paper.config;

import com.paper.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class CustomRestMvcConfiguration {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api/v1");//根URL
                config.exposeIdsFor(
                        User.class,
                        Website.class,
                        Record.class,
                        RecordGroup.class,
                        Result.class,
                        ResultGroup.class);
            }
        };
    }

}

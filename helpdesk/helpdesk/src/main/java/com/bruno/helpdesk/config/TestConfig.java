package com.bruno.helpdesk.config;

import com.bruno.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    @Bean
    public void instanciaDB(){
        this.dbService.instaciaDB();
    }

}

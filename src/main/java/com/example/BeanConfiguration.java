package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

/**
 * Created by mihailkopchev on 3/6/18.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

}

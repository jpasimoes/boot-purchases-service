package net.jpasimoes.service.purchases.impl.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by joao.simoes on 17/06/16.
 */
@Configuration
public class DozerConfiguration {

    @Bean
    public Mapper mapper(){
        return new DozerBeanMapper(Arrays.asList("classpath:dozer-global-config.xml"));
    }

}

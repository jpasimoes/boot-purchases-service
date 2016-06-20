package net.jpasimoes.service.purchases.configuration;

import net.jpasimoes.service.purchases.api.dao.PurchasesDao;
import net.jpasimoes.service.purchases.impl.controller.PurchasesController;
import net.jpasimoes.service.purchases.impl.service.PurchasesService;
import org.dozer.Mapper;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

/**
 * Created by joao.simoes on 18/06/16.
 */
@Configuration
@ComponentScan(basePackageClasses={PurchasesController.class, PurchasesService.class})
public class TestConfiguration {

    @Bean(name="mockedFactsService")
    @Primary
    public PurchasesService mockedPurchasesService(){
        return mock(PurchasesService.class);
    }

    @Bean
    @Primary
    public PurchasesDao mockedPurchaseDao(){
        return mock(PurchasesDao.class);
    }

    @Bean
    public Mapper mapper(){
        return mock(Mapper.class);
    }

    @Bean
    public CounterService counterService(){
        return mock(CounterService.class);
    }

    @Bean
    public GaugeService gaugeService(){
        return mock(GaugeService.class);
    }

}

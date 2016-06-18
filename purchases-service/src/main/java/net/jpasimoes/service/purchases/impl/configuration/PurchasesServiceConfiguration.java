package net.jpasimoes.service.purchases.impl.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joao.simoes on 16/06/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"net.jpasimoes.service.purchases"})
public class PurchasesServiceConfiguration {

}

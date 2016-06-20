package net.jpasimoes.service.purchases.impl.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Created by joao.simoes on 19/06/16.
 */
@Aspect
@Component
public class PurchasesServiceMetrics {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasesServiceMetrics.class);

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    @AfterReturning(pointcut = "execution(* net.jpasimoes.service.purchases.impl.service.PurchasesService.listPurchases())")
    public void afterCallingListPurchases() {
        LOGGER.debug("Triggered after calling listPurchases()");
        counterService.increment("counter.calls.list_purchases");
    }

    @AfterThrowing(pointcut = "execution(* net.jpasimoes.service.purchases.impl.service.PurchasesService.listPurchases())", throwing = "e")
    public void afterGetGreetingThrowsException(Exception e) {
        LOGGER.debug("Triggered after listPurchases() throws exception");
        counterService.increment("counter.errors.list_purchases");
    }

}

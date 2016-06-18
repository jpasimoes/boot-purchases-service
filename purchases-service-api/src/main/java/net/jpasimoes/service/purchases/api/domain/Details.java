package net.jpasimoes.service.purchases.api.domain;

/**
 * Created by joao.simoes on 15/06/16.
 */
public interface Details {

    long getId();

    long getPurchaseId();

    String getDescription();

    int getQuantity();

    double getValue();

}

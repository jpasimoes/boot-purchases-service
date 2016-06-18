package net.jpasimoes.service.purchases.api.domain;

import java.util.Date;

/**
 * Created by joao.simoes on 15/06/16.
 */
public interface Purchase {

    long getId();

    String getProductType();

    Date getExpires();

}

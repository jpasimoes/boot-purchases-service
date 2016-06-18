package net.jpasimoes.service.purchases.api.dao;

import net.jpasimoes.service.purchases.api.domain.Details;
import net.jpasimoes.service.purchases.api.domain.Purchase;

import java.util.List;

/**
 * Created by joao.simoes on 16/06/16.
 */
public interface PurchasesDao {

    List<Purchase> findAllPurchases();

    List<Details> findDetailsForPurchases(List<Long> purchaseIds);

}

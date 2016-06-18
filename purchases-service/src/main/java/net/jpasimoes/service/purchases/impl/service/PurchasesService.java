package net.jpasimoes.service.purchases.impl.service;

import net.jpasimoes.service.purchases.api.dao.PurchasesDao;
import net.jpasimoes.service.purchases.api.domain.Details;
import net.jpasimoes.service.purchases.api.domain.Purchase;
import net.jpasimoes.service.purchases.dtos.DetailsDto;
import net.jpasimoes.service.purchases.dtos.PurchaseDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by joao.simoes on 16/06/16.
 */
@Service
public class PurchasesService {

    @Autowired
    private PurchasesDao dao;

    @Autowired
    private Mapper mapper;

    /**
     * List all the valid {@link PurchaseDto}'s with the corresponding {@link DetailsDto}'s
     *
     * @return all the valid {@link PurchaseDto}'s
     */
    public List<PurchaseDto> listPurchases(){
        List<Purchase> validPurchases = getValidPurchases();
        List<Details> details = findDetailsForPurchasesById(extractPurchaseIds(validPurchases));

        return validPurchases.stream().map(purchase -> convertoToDto(purchase, details)).collect(Collectors.toList());
    }

    private PurchaseDto convertoToDto(Purchase purchase, List<Details> details){
        PurchaseDto dto = mapper.map(purchase, PurchaseDto.class);
        Optional<Details> purchaseDetails = findPurchaseDetails(details, dto.getId());
        if(purchaseDetails.isPresent()) {
            dto.setDetails(mapper.map(purchaseDetails.get(), DetailsDto.class));
        }
        return dto;
    }

    /**
     * Queries and filters the Purchases which are already expired
     * @return the valid list of {@link Purchase}'s
     */
    private List<Purchase> getValidPurchases() {
        List<Purchase> purchases = dao.findAllPurchases();
        return filterValidPurchases(purchases);
    }

    /**
     * Finds the Purchase {@link Details} of a given Purchase with id @param purchaseId
     * @param details
     * @param purchaseId
     * @return the {@link Details} for the Purchase with purchaseId
     */
    private Optional<Details> findPurchaseDetails(List<Details> details, long purchaseId) {
        return details.stream()
                .filter(d -> d.getPurchaseId() == purchaseId)
                .collect(Collectors.reducing((a, b) -> null));
    }

    /**
     * List all the Purchase {@link Details} for the purchaseIds provided
     * @param purchaseIds
     * @return
     */
    private List<Details> findDetailsForPurchasesById(List<Long> purchaseIds) {
        if(purchaseIds.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return dao.findDetailsForPurchases(purchaseIds);
    }

    /**
     * Extract the id's of the given {@link Purchase}'s
     * @param purchases the purchases to extract the id's
     * @return the purchases id's
     */
    private List<Long> extractPurchaseIds(List<Purchase> purchases) {
        return purchases
                    .stream()
                    .map(Purchase::getId)
                    .collect(toList());
    }

    /**
     * Filters the Purchases which get expired
     *
     * @param purchases the list of {@link Purchase}'s
     * @return the valid {@link Purchase}'s
     */
    private List<Purchase> filterValidPurchases(List<Purchase> purchases) {
        return purchases
                    .stream()
                    .filter(p -> p.getExpires().after(new Date())).collect(Collectors.toList());
    }

}

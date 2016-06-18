package net.jpasimoes.service.purchases.impl.controller;

import net.jpasimoes.service.purchases.api.domain.Purchase;
import net.jpasimoes.service.purchases.dtos.PurchaseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.jpasimoes.service.purchases.impl.service.PurchasesService;

import java.util.ArrayList;
import java.util.List;

/**
 * REST endpoint for the H2Purchase Resource<br>
 *
 * Created by joao.simoes on 16/06/16.
 */
@RestController
@RequestMapping(path="/purchases")
public class PurchasesController {

    private static final Logger LOG = LoggerFactory.getLogger(PurchasesController.class);

    @Autowired
    private PurchasesService purchasesService;

    /**
     * Queries the list of Purchases.
     *
     * @return 200 OK if there are Purchases to be returned, 204 No Content otherwise.
     */
    @RequestMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE, method= RequestMethod.GET)
    public ResponseEntity<List<PurchaseDto>> getAllPurchases(){
        LOG.debug("Calling operation to list purchases.");

        List<PurchaseDto> purchases = purchasesService.listPurchases();
        if(purchases.isEmpty()){
            LOG.debug("No Purchases available.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
}

package net.jpasimoes.service.purchases.controller;

import net.jpasimoes.service.purchases.api.dao.PurchasesDao;
import net.jpasimoes.service.purchases.configuration.TestConfiguration;
import net.jpasimoes.service.purchases.dtos.PurchaseDto;
import net.jpasimoes.service.purchases.impl.controller.PurchasesController;
import net.jpasimoes.service.purchases.impl.service.PurchasesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by joao.simoes on 18/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class, loader = SpringApplicationContextLoader.class)
public class PurchasesControllerShould {

    @Autowired
    private PurchasesController purchasesController;

    @Autowired
    private PurchasesService mockedPurchasesService;

    @Autowired
    private PurchasesDao mockedPurchasesDao;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(purchasesController)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DirtiesContext
    public void returnNoContentsIfPurchasesListIsEmpty() throws Exception{
        List<PurchaseDto> purchases = Collections.emptyList();
        when(mockedPurchasesService.listPurchases()).thenReturn(purchases);
        mockMvc.perform(get("/purchases/")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(mockedPurchasesService, times(1)).listPurchases();
    }

    @Test
    @DirtiesContext
    public void returnOkIfPurchasesListIsNotEmpty() throws Exception{
        PurchaseDto purchase = new PurchaseDto(1, "test", new Date());
        List<PurchaseDto> purchases = Arrays.asList(purchase);
        when(mockedPurchasesService.listPurchases()).thenReturn(purchases);
        mockMvc.perform(get("/purchases/")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].productType", is("test")));
        verify(mockedPurchasesService, times(1)).listPurchases();
    }

}

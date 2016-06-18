package net.jpasimoes.service.purchases.service;

import net.jpasimoes.service.purchases.api.dao.PurchasesDao;
import net.jpasimoes.service.purchases.api.domain.Details;
import net.jpasimoes.service.purchases.api.domain.Purchase;
import net.jpasimoes.service.purchases.configuration.TestConfiguration;
import net.jpasimoes.service.purchases.dtos.DetailsDto;
import net.jpasimoes.service.purchases.dtos.PurchaseDto;
import net.jpasimoes.service.purchases.impl.service.PurchasesService;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by joao.simoes on 18/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class, loader = SpringApplicationContextLoader.class)
public class PurchasesServiceShould {

    @Autowired
    @Qualifier("purchasesService")
    private PurchasesService purchasesService;

    @Autowired
    private PurchasesDao mockedDao;

    @Autowired
    private Mapper mockedMapper;

    @Test
    public void returnAnEmptyListWhenDaoReturnsNoResults(){
        when(mockedDao.findAllPurchases()).thenReturn(Collections.emptyList());
        List<PurchaseDto> purchasesDtos = purchasesService.listPurchases();
        assertThat(purchasesDtos).isEmpty();
    }

    @Test
    public void returnAnEmptyListWhenDaoReturnsExpiredPurchases(){
        Purchase mockedPurchase = mock(Purchase.class);
        when(mockedPurchase.getExpires()).thenReturn(new GregorianCalendar(2015,1,1).getTime());
        when(mockedDao.findAllPurchases()).thenReturn(Arrays.asList(mockedPurchase));
        List<PurchaseDto> purchasesDtos = purchasesService.listPurchases();
        assertThat(purchasesDtos).isEmpty();
    }

    @Test
    public void returnAnPurchaseDtoWithEmptyDetailsWhenDaoReturnsNoDetailsForPurchaseId(){
        Purchase mockedPurchase = mock(Purchase.class);
        PurchaseDto mockedPurchaseDto = mock(PurchaseDto.class);
        when(mockedPurchase.getExpires()).thenReturn(new GregorianCalendar(2020,1,1).getTime());
        when(mockedMapper.map(mockedPurchase, PurchaseDto.class)).thenReturn(mockedPurchaseDto);
        when(mockedDao.findAllPurchases()).thenReturn(Arrays.asList(mockedPurchase));
        when(mockedDao.findDetailsForPurchases(Arrays.asList(mockedPurchase.getId()))).thenReturn(Collections.emptyList());
        List<PurchaseDto> purchasesDtos = purchasesService.listPurchases();
        assertThat(purchasesDtos).hasSize(1);
        assertThat(purchasesDtos.get(0).getDetails()).isNull();
    }

    @Test
    public void returnAPurchaseDtoWithDetailsWhenDaoReturnsDetailsForPurchaseId(){
        Purchase mockedPurchase = mock(Purchase.class);
        Details mockedDetails = mock(Details.class);
        PurchaseDto purchaseDto = new PurchaseDto(1L, "Test", new GregorianCalendar(2020,1,1).getTime());
        DetailsDto detailsDto = new DetailsDto(1L, 10, 30d);

        when(mockedPurchase.getId()).thenReturn(1L);
        when(mockedPurchase.getExpires()).thenReturn(new GregorianCalendar(2020,1,1).getTime());
        when(mockedDetails.getPurchaseId()).thenReturn(1L);
        when(mockedMapper.map(mockedPurchase, PurchaseDto.class)).thenReturn(purchaseDto);
        when(mockedMapper.map(mockedDetails, DetailsDto.class)).thenReturn(detailsDto);
        when(mockedDao.findAllPurchases()).thenReturn(Arrays.asList(mockedPurchase));
        when(mockedDao.findDetailsForPurchases(Arrays.asList(mockedPurchase.getId()))).thenReturn(Arrays.asList(mockedDetails));

        List<PurchaseDto> purchasesDtos = purchasesService.listPurchases();
        assertThat(purchasesDtos).hasSize(1);
        assertThat(purchasesDtos.get(0).getDetails()).isNotNull();
    }

}

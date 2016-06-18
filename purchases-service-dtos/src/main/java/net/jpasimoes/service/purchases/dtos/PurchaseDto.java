package net.jpasimoes.service.purchases.dtos;

import java.util.Date;

/**
 * Created by joao.simoes on 17/06/16.
 */
public class PurchaseDto {

    private long id;
    private String productType;
    private Date expires;
    private DetailsDto details;

    public PurchaseDto() {}

    public PurchaseDto(long id, String productType, Date expires){
        this.id = id;
        this.productType = productType;
        this.expires = expires;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public DetailsDto getDetails() {
        return details;
    }

    public void setDetails(DetailsDto details) {
        this.details = details;
    }
}

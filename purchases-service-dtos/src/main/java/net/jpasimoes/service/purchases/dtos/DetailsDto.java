package net.jpasimoes.service.purchases.dtos;

/**
 * Created by joao.simoes on 17/06/16.
 */
public class DetailsDto {

    private long id;
    private String description;
    private int quantity;
    private double value;

    public DetailsDto() {
    }

    public DetailsDto(long id, int quantity, double value) {
        this.id = id;
        this.quantity = quantity;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

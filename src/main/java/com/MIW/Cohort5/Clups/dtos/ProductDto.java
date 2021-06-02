package com.MIW.Cohort5.Clups.dtos;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes products with unnecessary data filtered out.
 */

public class ProductDto {

     private String name;

    private double price;

    // TODO: 01/06/2021 Kijk hier nog even naar: is deze constructor nodig? en getters?
/*
     public ProductDto(String name, Double price) {
     this.name = name;
     this.price = price;
    }
 */

    public ProductDto() {}

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {this.name = name;}

    public void setPrice(double price) {this.price = price;}


}

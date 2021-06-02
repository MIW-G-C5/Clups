package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 */
public class OrderedItemDto {

    private ProductDto orderedProduct;
    private int count;

    public OrderedItemDto(ProductDto productDto, int count) {
        this.orderedProduct = productDto;
        this.count = count;
    }

    public void addCount() {
        this.count++;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal priceNotRounded = orderedProduct.getProductPrice().multiply(new BigDecimal(count));

        BigDecimal priceRounded = priceNotRounded.setScale(2, RoundingMode.HALF_EVEN);

        return priceRounded;
    }

    public ProductDto getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(ProductDto orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

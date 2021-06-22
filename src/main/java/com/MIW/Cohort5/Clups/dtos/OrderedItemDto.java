package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This is a line of the order, with the ordered product and the amount of times the product has been ordered.
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

    public void decreaseCount() {
        this.count--;
    }

    public ProductDto getOrderedProduct() {
        return orderedProduct;
    }

    public int getCount() {
        return count;
    }

}

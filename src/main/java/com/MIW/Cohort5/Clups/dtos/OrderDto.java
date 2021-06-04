package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * A collection of Products a customer wants to order
 */

public class OrderDto {

    List<OrderedItemDto> orderedItems;

    public OrderDto() {
        this.orderedItems = new ArrayList<>();
    }

    public List<OrderedItemDto> getOrderedItems() {
        return orderedItems;
    }

    public void addToOrder(ProductDto orderedProduct) {
        int index = getIndexFromProductInOrderList(orderedProduct);

        if (index >= 0) {
            addCountToExistingItem(index);
        } else {
            addNewItem(orderedProduct);
        }
    }

    private void addCountToExistingItem(int index) {
        orderedItems.get(index).addCount();
    }

    private void addNewItem(ProductDto orderedProduct) {
        OrderedItemDto orderedItem = new OrderedItemDto(orderedProduct, 1);
        orderedItems.add(orderedItem);
    }

    public void removeFromOrder(ProductDto product) {
        int index = getIndexFromProductInOrderList(product);

        if (orderedItems.get(index).getCount() > 1) {
            orderedItems.get(index).decreaseCount();
        } else {
            orderedItems.remove(index);
        }
    }


    private int getIndexFromProductInOrderList(ProductDto orderedProduct) {
        int index = -1;

        for (int i = 0; i < orderedItems.size(); i++) {
            if (orderedItems.get(i).getOrderedProduct().equals(orderedProduct)) {
                index = i;
            }
        }

        return index;
    }

    public BigDecimal calculateTotalCostOrder() {
        BigDecimal totalCost = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

        for (OrderedItemDto orderedItem : orderedItems) {
            totalCost = totalCost.add(orderedItem.calculateTotalPrice());
        }

        return totalCost;
    }

}

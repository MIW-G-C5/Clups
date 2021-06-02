package com.MIW.Cohort5.Clups.dtos;

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

    public void setOrderedItems(List<OrderedItemDto> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addToOrder(ProductDto orderedProduct) {
        if (orderedItems.isEmpty()) {
            addNewItem(orderedProduct);
        } else if (orderedItems.contains(orderedProduct)) {
            addCountToExistingItem(orderedProduct);
        } else {
            addNewItem(orderedProduct);
        }
    }

    private void addCountToExistingItem(ProductDto orderedProduct) {
        for (int i = 0; i < orderedItems.size(); i++) {
            if (orderedItems.get(i).getOrderedProduct().equals(orderedProduct)) {
                orderedItems.get(i).addCount();
            }
        }
    }

    private void addNewItem(ProductDto orderedProduct) {
        OrderedItemDto orderedItem = new OrderedItemDto(orderedProduct, 1);
        orderedItems.add(orderedItem);
    }
}

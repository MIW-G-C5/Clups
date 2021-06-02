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

    public void addToOrder(ProductDto orderedProduct) {
        int index = checkItemPresentInList(orderedProduct);

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

    private int checkItemPresentInList(ProductDto orderedProduct) {
        int index = -1;

        for (int i = 0; i < orderedItems.size(); i++) {
            if (orderedItems.get(i).getOrderedProduct().equals(orderedProduct)) {
                index = i;
            }
        }

        return index;
    }
}

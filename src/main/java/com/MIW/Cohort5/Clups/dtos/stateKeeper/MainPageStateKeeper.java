package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.UserDto;

import java.util.List;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the Main Page.
 */

public class MainPageStateKeeper {

    private Integer categoryCode;
    private OrderDto order;
    private List<UserDto> sortedUsers;

    // this should always be false, unless changed by user actions in the application
    private boolean showModal = false;

    private UserDto selectedCustomer;

    public MainPageStateKeeper() { }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public List<UserDto> getSortedUsers() {
        return sortedUsers;
    }

    public void setSortedUsers(List<UserDto> sortedUsers) {
        this.sortedUsers = sortedUsers;
    }

    public boolean isShowModal() {
        return showModal;
    }

    public void setShowModal(boolean showModal) {
        this.showModal = showModal;
    }

    public UserDto getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(UserDto selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
}

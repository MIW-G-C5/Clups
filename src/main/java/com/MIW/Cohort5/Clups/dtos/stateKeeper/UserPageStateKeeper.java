package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.CustomerDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 */
public class CustomerAccountPageStateKeeper {

    private CustomerDto currentCustomer;

    //this should always be false, unless changed by user input in the application
    private boolean showForm = false;

    public CustomerAccountPageStateKeeper() {
    }

    public void clearCustomer() {
        currentCustomer = null;
    }

    public CustomerDto getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(CustomerDto currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }
}

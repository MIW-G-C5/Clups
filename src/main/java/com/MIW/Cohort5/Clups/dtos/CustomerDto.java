package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * A description of a Customer without exposing unnecessary details to the View
 */
public class CustomerDto {

    private Integer customerCode;

    private String firstName;
    private String infixName;
    private String lastName;

    private BigDecimal prepaidBalance;

    public CustomerDto() {
    }

    public String getFullName() {
        return firstName + " " + infixName + " " + lastName;
    }

    public Integer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Integer customerCode) {
        this.customerCode = customerCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInfixName() {
        return infixName;
    }

    public void setInfixName(String infixName) {
        this.infixName = infixName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getPrepaidBalance() {
        return prepaidBalance;
    }

    public void setPrepaidBalance(BigDecimal prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }
}

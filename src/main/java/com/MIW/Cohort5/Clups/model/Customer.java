package com.MIW.Cohort5.Clups.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * A customer has an account in the application for prepaid payment options
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerDbId;

    @Column(unique = true)
    private Integer customerCode;

    private String firstName;
    private String infixName;
    private String lastName;

    private BigDecimal prepaidBalance;

    public Customer() {
    }

    public Customer(String firstName, String infixName, String lastName, BigDecimal prepaidBalance) {
        this.firstName = firstName;
        this.infixName = infixName;
        this.lastName = lastName;
        this.prepaidBalance = prepaidBalance;
    }

    public Integer getCustomerDbId() {
        return customerDbId;
    }

    public void setCustomerDbId(Integer customerDbId) {
        this.customerDbId = customerDbId;
    }

    public Integer getCustomerCode() {
        // this method cannot return null to ensure no nullPointerExceptions in the application
        if (customerCode == null) {
            return -1;
        } else {
            return customerCode;
        }
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

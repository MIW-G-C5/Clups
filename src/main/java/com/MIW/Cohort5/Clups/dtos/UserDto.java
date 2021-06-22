package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class describes users without exposing unnecessary details to the View.
 */

public class UserDto {

    private Integer userCode;

    private String username;
    private String password;
    private String fullName;
    private BigDecimal prepaidBalance;

    public UserDto() {}

    public UserDto(String fullName, BigDecimal prepaidBalance) {
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
    }

    public UserDto(String username, String password, String fullName, BigDecimal prepaidBalance) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
    }

    public UserDto(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.prepaidBalance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Integer getUserCode() {
        // this method cannot return null to ensure no nullPointerExceptions in the application
        if (userCode == null) {
            return -1;
        } else {
            return userCode;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public BigDecimal getPrepaidBalance() {
        return prepaidBalance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPrepaidBalance(BigDecimal prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }

}

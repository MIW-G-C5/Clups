package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

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
    private String role;

    public UserDto() {}

    public UserDto(String fullName, BigDecimal prepaidBalance) {
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getRole() {
        return role;
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

    public void setRole(String role) {
        this.role = role;
    }
}

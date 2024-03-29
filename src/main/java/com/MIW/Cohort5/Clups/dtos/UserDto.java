package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class describes users without exposing unnecessary details to the View.
 */

public class UserDto implements Comparable<UserDto> {

    private Integer userCode;

    private String username;
    private String password;
    private String fullName;
    private BigDecimal prepaidBalance;
    private String userRole;

    public UserDto() {}

    public UserDto(String username, String password, String fullName, BigDecimal prepaidBalance, String userRole) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
        this.userRole = userRole;
    }

    public Integer getUserCode() {
        // this method cannot return null to ensure no nullPointerExceptions in the application
        if (userCode == null) {
            return -1;
        } else {
            return userCode;
        }
    }

    @Override
    public int compareTo(UserDto other) {
        return this.getFullName().compareTo(other.getFullName());
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

    public String getUserRole() {
        return userRole;
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

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}

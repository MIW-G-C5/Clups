package com.MIW.Cohort5.Clups.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * A user has a role. Depending on the role, it has certain privileges and attributes.
 */

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer userId;

    @Column(unique = true)
    private Integer userCode;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String fullName;
    private BigDecimal prepaidBalance;
    private String role;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public User(){}

    public User(String fullName, BigDecimal prepaidBalance) {
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }

    public Integer getUserId() {
        return userId;
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

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserCode(Integer customerCode) {
        this.userCode = customerCode;
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


package com.MIW.Cohort5.Clups.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * A user has a role. Depending on the role, it has certain privileges and attributes.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    private Integer userCode;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String fullName;
    private BigDecimal prepaidBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public User(String username, String password, String fullName, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public User(String fullName, BigDecimal prepaidBalance) {
        this.fullName = fullName;
        this.prepaidBalance = prepaidBalance;
    }

    public User(){}

    public Integer getUserCode() {
        // this method cannot return null to ensure no nullPointerExceptions in the application
        if (userCode == null) {
            return -1;
        } else {
            return userCode;
        }
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getPrepaidBalance() {
        return prepaidBalance;
    }

    public void setPrepaidBalance(BigDecimal prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }

    public Role getRole() {
        return role;
    }

}


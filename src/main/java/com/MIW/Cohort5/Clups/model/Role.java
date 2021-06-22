package com.MIW.Cohort5.Clups.model;

import javax.persistence.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 */

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    @ManyToOne
    private User user;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

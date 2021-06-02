package com.MIW.Cohort5.Clups.dtos;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class describes users with unnecessary data filtered out.
 */

public class UserDto {

    private String username;

    private String password;

    public UserDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

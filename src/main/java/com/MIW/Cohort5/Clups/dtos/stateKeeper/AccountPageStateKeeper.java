package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.UserDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 */
public class AccountPageStateKeeper {

    private UserDto userDto;

    //this should always be false, unless changed by user input in the application
    private boolean showForm = false;

    public AccountPageStateKeeper() {
    }

    public void clearUser() {
        userDto = null;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }
}

package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.UserDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 */
public class AccountPageStateKeeper {

    private UserDto currentUserDto;
    private String fullUserName;

    //this should always be false, unless changed by user input in the application
    private boolean showUserForm = false;

    public AccountPageStateKeeper() {
    }

    public void clearUser() {
        currentUserDto = null;
    }

    public UserDto getCurrentUserDto() {
        return currentUserDto;
    }

    public void setCurrentUserDto(UserDto currentUserDto) {
        this.currentUserDto = currentUserDto;
    }

    public boolean isShowUserForm() {
        return showUserForm;
    }

    public void setShowUserForm(boolean showUserForm) {
        this.showUserForm = showUserForm;
    }
}

package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.UserDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the Account Page.
 */
public class AccountPageStateKeeper {

    private UserDto currentUserDto;

    //these should always be false, unless changed by user input in the application
    private boolean showUserForm = false;
    private boolean userSelected = false;

    public AccountPageStateKeeper() {
    }

    public void clearUser() {
        currentUserDto = null;
        userSelected = false;
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

    public boolean isUserSelected() {
        return userSelected;
    }

    public void setUserSelected(boolean userSelected) {
        this.userSelected = userSelected;
    }
}

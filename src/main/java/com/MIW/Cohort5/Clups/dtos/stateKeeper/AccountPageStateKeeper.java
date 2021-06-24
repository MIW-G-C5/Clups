package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.UserDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the Account Page.
 */
public class AccountPageStateKeeper {

    //these constants show where in the add/editUser-process the active user is
    private final static int START_USER_PROCESS = 0;
    private final static int ADD_USER = 1;
    private final static int USER_SELECTED = 2;
    private final static int EDIT_USER = 3;
    private final static int ADD_CREDIT = 4;

    // this keeps track of where in the add/editUser-process the active user is, using the constants above.
    // it always starts with START_USER_PROCESS. User actions can change it to a different status.
    private int processStep = START_USER_PROCESS;

    private UserDto currentUserDto;

    public AccountPageStateKeeper() {
    }

    public void clearUser() {
        currentUserDto = null;
        processStart();
    }

    public void processStart() {
        processStep = START_USER_PROCESS;
    }

    public void processAdduser() {
        processStep = ADD_USER;
    }

    public void processUserSelected() {
        processStep = USER_SELECTED;
    }

    public void processEditUser() {
        processStep = EDIT_USER;
    }

    public void processAddCredit() {
        processStep = ADD_CREDIT;
    }

    public int getProcessStep() {
        return processStep;
    }

    public UserDto getCurrentUserDto() {
        return currentUserDto;
    }

    public void setCurrentUserDto(UserDto currentUserDto) {
        this.currentUserDto = currentUserDto;
    }
    
}

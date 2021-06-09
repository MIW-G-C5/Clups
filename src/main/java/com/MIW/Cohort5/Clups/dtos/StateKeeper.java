package com.MIW.Cohort5.Clups.dtos;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session.
 */

public class StateKeeper {

    private String categoryName;

    public StateKeeper() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

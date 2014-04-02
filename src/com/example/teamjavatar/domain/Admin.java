package com.example.teamjavatar.domain;

public class Admin extends AbstractUser {

    // private String userID;

    /**
     * Construct an admin.
     */
    public Admin() {
        super("admin");
    }

    // @Override
    // public String getID() {
    // return userID;
    // }

    public void changePassword(String userID) {
        // TODO reset/change the password of the specified user
        // just call on change password in the database
        // admin might need a list of users that require password changes,
        // and its own database table for this
    }

}
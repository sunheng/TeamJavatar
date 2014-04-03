package com.example.teamjavatar.domain;

/**
 * Admin class that has characteristics of users.
 * @author Team Javatar
 *
 */
public class Admin extends AbstractUser {

    // private String userID;

    /**
     * Construct an admin.
     */
    public Admin() {
        super("admin");
    }

    /**
     * Change password of another user.
     * @param userID UserID
     */
    public void changePassword(String userID) {
        // TODO reset/change the password of the specified user
        // just call on change password in the database
        // admin might need a list of users that require password changes,
        // and its own database table for this
    }

}
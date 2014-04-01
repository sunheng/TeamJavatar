package com.example.teamjavatar.domain;

/*
 * pure fabrication pattern
 * exists to facilitate usage of user and admin object at the same time
 */
public abstract class AbstractUser {

    protected String userID;

    public AbstractUser(String ID) {
        this.userID = ID;
    }

    public String getID() {
        return this.userID;
    }

}
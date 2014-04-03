package com.example.teamjavatar.domain;

/*
 * pure fabrication pattern
 * exists to facilitate usage of user and admin object at the same time
 */

/**
 * Set up abstract class for User and Admin.
 * @author Team Javatar
 *
 */
public abstract class AbstractUser {

    /** User ID. */
    protected String userID;

    /** 
     * Constructor. 
     * @param id The id of user
     */
    public AbstractUser(String id) {
        this.userID = id;
    }

    /** 
     * Set userID. 
     * @param userIDInput The userID can only be set for user
     */
    public void setUserID(String userIDInput) {
        this.userID = userIDInput;
    }
    
    /**
     * Get the id of user.
     * @return user ID
     */
    public String getID() { //This is justified due the non ambiguity and already deeply implemented in the system.
        return this.userID;
    }

}
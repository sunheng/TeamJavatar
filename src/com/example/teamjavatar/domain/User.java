package com.example.teamjavatar.domain;

/**
 * User class that represents the user of the application.
 * @author Team Javatar
 * 
 */
public class User extends AbstractUser {

    /** First name. */
    private String firstName;
    /** Last name. */
    private String lastName;

    /**
     * Constructor for creating a user.
     * 
     * @param userID UserID
     * @param firstNameInput First Name
     * @param lastNameInput Last Name
     */
    public User(String userID, String firstNameInput, String lastNameInput) {
        super(userID);
        this.firstName = firstNameInput;
        this.lastName = lastNameInput;
    }

//    /** THIS METHOD HAS BEEN MOVED TO ABSTRACT USER
//     * Set the userID.
//     * @param userIDInput UserID to set
//     */
//    public void setID(String userIDInput) { //This is justifiable as ID is from abstract class
//        this.userID = userIDInput;
//    }

    /**
     * Get the first name of user.
     * @return First name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name of user.
     * @param firstNameInput New first name
     */
    public void setFirstName(String firstNameInput) {
        this.firstName = firstNameInput;
    }

    /**
     * Get last name of user.
     * @return The last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set user last name.
     * @param lastNameInput Last name 
     */
    public void setLastName(String lastNameInput) {
        this.lastName = lastNameInput;
    }

    /**
     * Get full name of user.
     * @return The user full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

//    /** DO WE NEED THIS?
//     * 
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    public AbstractReport makeSpendingReport(long startDate, long endDate) {
//        // TODO process and obtain withdrawals
//        // return report
//        return null;
//    }
//
//    /**
//     * 
//     */
//    public void changePassword() {
//        // TODO
//        // not sure if this should be here or as part of the database
//        // i think just call on requestChangePassword on database.
//    }

}

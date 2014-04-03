package com.example.teamjavatar.domain;

/*
 * interface segregation principle
 * this is a small interface which forces implementations to work with arrayadapters
 */
/**
 * toString method is necessary to work with ArrayAdapter.
 * 
 * @author Team Javatar
 * 
 */
public interface ListItem {

    /** Interface method to get ID of item. 
     * @return The id of that item 
     */
    int getID();

    /**
     * Convert all items to string list.
     * @return Text list of items
     */
    String toString();

}

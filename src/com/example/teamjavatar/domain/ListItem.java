package com.example.teamjavatar.domain;

/*
 * interface segregation principle
 * this is a small interface which forces implementations to work with arrayadapters
 */
/**
 * toString method is necessary to work with ArrayAdapter
 * 
 * @author Brian
 * 
 */
public interface ListItem {

    public int getID();

    public String toString();

}

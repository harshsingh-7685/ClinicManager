package com.manager;

import java.util.Date;

/**
 * Represents a visit.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Visit
{
    /** The date of the visit */
    private Date date;
    /** The description of the visit */
    private String description;
    /** The next visit */
    private Visit next;

    /**
     * Constructor for Visit class.
     * 
     * @param date        the date of the visit
     * @param description the description of the visit
     */
    public Visit(Date date, String description)
    {
        this.date = date;
        this.description = description;
        this.next = null;
    }

    /**
     * Returns the date of the visit.
     * 
     * @return the date of the visit
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Returns the description of the visit.
     * 
     * @return the description of the visit
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns the next visit.
     * 
     * @return the next visit
     */
    public Visit getNext()
    {
        return next;
    }

    /**
     * Sets the next visit.
     * 
     * @param next the next visit
     */
    public void setNext(Visit next)
    {
        this.next = next;
    }

    /**
     * Returns a string representation of the visit.
     * 
     * @return a string representation of the visit
     */
    @Override
    public String toString()
    {
        return "Visit on " + date + ": " + description;
    }
}

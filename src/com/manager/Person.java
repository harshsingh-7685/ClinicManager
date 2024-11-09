package com.manager;

/**
 * Represents a person with a profile.
 * This class is the superclass for Patient and Provider classes.
 * It implements Comparable to allow comparison based on the profile.
 * 
 * @author Surya Bhardwaj
 */
public class Person implements Comparable<Person> {
    /** The profile of the person */
    protected Profile profile;

    /**
     * Constructs a Person with the given profile.
     * 
     * @param profile the profile of the person
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Gets the profile of the person.
     * 
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the profile of the person.
     * 
     * @param profile the new profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Compares this person with another person based on their profiles.
     * 
     * @param other the other person to compare to
     * @return a negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person
     */
    @Override
    public int compareTo(Person other) {
        return this.profile.compareTo(other.profile);
    }

    /**
     * Checks if this person is equal to another object.
     * 
     * @param obj the object to compare to
     * @return true if the object is a Person with the same profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Person))
            return false;
        Person other = (Person) obj;
        return this.profile.equals(other.profile);
    }

    /**
     * Returns a string representation of the person.
     * 
     * @return the string representation of the profile
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    /**
     * Returns the hash code of this person.
     * 
     * @return the hash code based on the profile
     */
    @Override
    public int hashCode() {
        return profile.hashCode();
    }
}

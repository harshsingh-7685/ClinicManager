package com.manager;

/**
 * Represents a profile with a first name, last name, and date of birth.
 * This class implements Comparable to allow comparison based on the profile
 * details.
 * 
 * @author Harsh Singh
 * @author Surya Bhardwaj
 */
public class Profile implements Comparable<Profile>
{
    /**First name of the person */
    private String firstName;
    /**Last name of the person */
    private String lastName;
    /**Date of birth of the person in MM/DD/YYYY format*/
    private Date dateOfBirth;

    /**
     * Constructs a Profile with the given first name, last name, and date of birth.
     * 
     * @param firstName   the first name of the person
     * @param lastName    the last name of the person
     * @param dateOfBirth the date of birth of the person
     */
    public Profile(String firstName, String lastName, Date dateOfBirth)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the first name of the person.
     * 
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     * 
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Gets the date of birth of the person.
     * 
     * @return the date of birth
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Compares this profile with another profile based on last name, first name,
     * and date of birth.
     * 
     * @param other the other profile to compare to
     * @return a negative integer, zero, or a positive integer as this profile
     *         is less than, equal to, or greater than the specified profile
     */
    @Override
    public int compareTo(Profile other)
    {
        // Compare last names
        int lastNameComparison = this.lastName.compareTo(other.lastName);
        if (lastNameComparison != 0)
        {
            return lastNameComparison;
        }

        // Compare first names
        int firstNameComparison = this.firstName.compareTo(other.firstName);
        if (firstNameComparison != 0)
        {
            return firstNameComparison;
        }

        // Compare dates of birth
        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    /**
     * Checks if this profile is equal to another object.
     * 
     * @param obj the object to compare to
     * @return true if the object is a Profile with the same first name, last name,
     *         and date of birth, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Profile))
            return false;

        Profile other = (Profile) obj;

        return firstName.equals(other.firstName) &&
               lastName.equals(other.lastName) &&
               dateOfBirth.equals(other.dateOfBirth);
    }

    /**
     * Returns the hash code of this profile.
     * Custom implementation to avoid using Object.hashCode()
     * 
     * @return the hash code based on the first name, last name, and date of birth
     */
    @Override
    public int hashCode()
    {
        int result = 17; // Start with a non-zero prime number
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the profile.
     * 
     * @return the string representation of the profile
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName + " " + dateOfBirth;
    }
}
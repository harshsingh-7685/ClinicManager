package com.manager;

/**
 * Represents a patient with a profile.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Patient extends Person
{
    /**First name of the patient */
    private String firstName;
    /**Last name of the patient */
    private String lastName;
    /**Date of birth of the patient */
    private Date dob;

    /**
     * Constructs a new patient with the given first name, last name, and date of birth.
     * 
     * @param firstName the first name
     * @param lastName  the last name
     * @param dob       the date of birth
     */
    public Patient(String firstName, String lastName, Date dob)
    {
        super(new Profile(firstName, lastName, dob));
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    /**
     * Constructs a new patient with the given profile.
     * 
     * @param profile the profile
     */
    public Patient(Profile profile)
    {
        super(profile);
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.dob = profile.getDateOfBirth();
    }

    /**
     * Returns the first name of the patient.
     * 
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Returns the last name of the patient.
     * 
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Returns the date of birth of the patient.
     * 
     * @return the toString() of the date of birth
     */
    public String getDob()
    {
        return dob.toString();
    }

    /**
     * Returns a string representation of the patient.
     * 
     * @return the string representation
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName + " " + dob.toString();
    }
}

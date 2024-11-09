package com.manager;

/**
 * Helper class for printBillingStatement() method in Scheduler.java
 * 
 * @author Harsh Singh
 */
public class PatientBilling implements Comparable<PatientBilling>
{
    /**Patient */
    private Person patient;
    /**Amount due */
    private int amountDue;

    /**
     * Constructor method. Sets patient and amountDue
     * 
     * @param patient   Patient to be added
     * @param amountDue Amount due to be added
     */
    public PatientBilling(Person patient, int amountDue)
    {
        this.patient = patient;
        this.amountDue = amountDue;
    }

    /**
     * Getter for patient
     * 
     * @return Return the patient
     */
    public Person getPatient()
    {
        return patient;
    }

    /**
     * Getter for amountDue
     * 
     * @return Return the amountDue
     */
    public int getAmountDue()
    {
        return amountDue;
    }

    /**
     * Adds amount to amountDue
     * 
     * @param amount Amount to be added
     */
    public void addAmount(int amount)
    {
        this.amountDue += amount;
    }

    /**
     * Our implementation of compareTo
     * 
     * @param other the object to be compared
     * @return return the -1, 0, or 1 based on the comparison
     */
    @Override
    public int compareTo(PatientBilling other)
    {
        int lastNameComparison = this.getPatient().getProfile().getLastName().compareTo(other.getPatient().getProfile().getLastName());

        if (lastNameComparison != 0)
        {
            return lastNameComparison;
        }

        int firstNameComparison = this.getPatient().getProfile().getFirstName().compareTo(other.getPatient().getProfile().getFirstName());

        if (firstNameComparison != 0)
        {
            return firstNameComparison;
        }

        return this.getPatient().getProfile().getDateOfBirth().compareTo(other.getPatient().getProfile().getDateOfBirth());
    }
}

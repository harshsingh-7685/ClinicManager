package com.manager;

/**
 * Represents a doctor with a specialty and NPI.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Doctor extends Provider
{
    /** The specialty of the doctor */
    private Specialty specialty;
    /** The NPI of the doctor */
    private String npi;

    /**
     * Constructs a new doctor with the given profile, location, specialty, and NPI.
     * 
     * @param profile   the profile of the doctor
     * @param location  the location of the doctor
     * @param specialty the specialty of the doctor
     * @param npi       the NPI of the doctor
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi)
    {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Returns the rate of the specialty of the doctor.
     * 
     * @return the specialty of the doctor
     */
    @Override
    public int rate()
    {
        switch (this.specialty)
        {
            case FAMILY:
                return 250;
            case PEDIATRICIAN:
                return 300;
            case ALLERGIST:
                return 350;
            default:
                return 0; // In case the specialty is not recognized
        }
    }

    /**
     * Returns the NPI of the doctor.
     * 
     * @return the NPI of the doctor
     */
    public String getNPI()
    {
        return npi;
    }

    /**
     * Returns a string representation of the doctor.
     * 
     * @return a string representation of the doctor
     */
    @Override
    public String toString()
    {
        return String.format("[%s, %s][%s, #%s]",
            getProfile(), getLocation(), specialty, npi);
    }
}

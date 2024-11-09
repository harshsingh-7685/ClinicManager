package com.manager;

/**
 * Represents the specialty of a provider.
 * This enum is used in the Provider class.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public enum Specialty
{
    /** Family specialty rate of $100 */
    FAMILY(100),

    /** Pediatrician specialty rate of $150 */
    PEDIATRICIAN(150),

    /** Allergist specialty rate of $200 */
    ALLERGIST(200);

    /** The rate of the specialty */
    private final int rate;

    /**
     * Constructor for Specialty enum.
     * 
     * @param rate the rate of the specialty
     */
    Specialty(int rate)
    {
        this.rate = rate;
    }

    /**
     * Returns the rate of the specialty.
     * 
     * @return the rate of the specialty
     */
    public int getRate()
    {
        return rate;
    }
}

package com.manager;

/**
 * Enum representing types of imaging services.
 * This enum includes CATSCAN, ULTRASOUND, and XRAY as imaging service types.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public enum Radiology
{
    /** Catscan imaging service. */
    CATSCAN,

    /** Ultrasound imaging service. */
    ULTRASOUND,

    /** X-ray imaging service. */
    XRAY;

    /**
     * Get the type of imaging service.
     * 
     * @return the type of imaging service
     */
    public String getType()
    {
        return this.toString();
    }

    /**
     * Get the type of imaging service in uppercase.
     * 
     * @return the type of imaging service
     */
    @Override
    public String toString()
    {
        return super.toString().toUpperCase();
    }
}

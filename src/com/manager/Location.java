package com.manager;

/**
 * Represents the location of a provider.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Location
{
    /** The city */
    private String city;
    /** The county */
    private String county;
    /** The zip code */
    private String zipCode;

    /** Custom data structure for city to county and zip code mapping */
    private static final String[][] cityToCountyAndZip = {
        {"BRIDGEWATER", "Somerset", "08807"},
        {"EDISON", "Middlesex", "08817"},
        {"CLARK", "Union", "07066"},
        {"PISCATAWAY", "Middlesex", "08854"},
        {"PRINCETON", "Mercer", "08542"},
        {"MORRISTOWN", "Morris", "07960"}
        // Add more cities as needed
    };

    /**
     * Constructor for Location
     * 
     * @param city the city
     */
    public Location(String city)
    {
        this.city = city;
        boolean found = false;
        for (String[] entry : cityToCountyAndZip)
        {
            if (entry[0].equalsIgnoreCase(city))
            {
                this.county = entry[1];
                this.zipCode = entry[2];
                found = true;
                break;
            }
        }
        if (!found)
        {
            this.county = "Unknown";
            this.zipCode = "00000";
        }
    }

    /**
     * Returns the city
     * 
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Returns the county
     * 
     * @return the county
     */
    public String getCounty()
    {
        return county;
    }

    /**
     * Returns the zip code
     * 
     * @return the zip code
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * toString method for Location
     * 
     * @return the city, county and zip code
     */
    @Override
    public String toString()
    {
        return city + ", " + county + " " + zipCode;
    }
}

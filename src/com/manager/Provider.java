package com.manager;

/**
 * Abstract class representing a provider with a location.
 * This class extends Person and serves as a base for specific provider types.
 * It includes an abstract method for determining the rate per visit.
 * 
 * @author Harsh Singh
 * @author Surya Bhardwaj
 */
public abstract class Provider extends Person
{
    /** The location of the provider's practice */
    private Location location;
    /** The availability of the provider's office during the day */
    private boolean[] availability;

    /**
     * Constructs a Provider with the given profile and location.
     * 
     * @param profile  the profile of the provider
     * @param location the location of the provider's practice
     */
    public Provider(Profile profile, Location location)
    {
        super(profile);
        this.location = location;
        this.availability = new boolean[12]; // 12 time slots per day

        for (int i = 0; i < 12; i++)
            availability[i] = true; // Initially, all slots are available
    }

    /**
     * Maps the Timeslot to the corresponding index in the availability array.
     * 
     * @param timeSlot the Timeslot to map
     * @return the index in the availability array or -1 if the time is invalid
     */
    private int getSlotIndex(Timeslot timeSlot)
    {
        int hour = timeSlot.getHour();
        int minute = timeSlot.getMinute();

        // Morning slots
        if (hour == 9 && minute == 0) return 0;    // 9:00 AM
        if (hour == 9 && minute == 30) return 1;   // 9:30 AM
        if (hour == 10 && minute == 0) return 2;   // 10:00 AM
        if (hour == 10 && minute == 30) return 3;  // 10:30 AM
        if (hour == 11 && minute == 0) return 4;   // 11:00 AM
        if (hour == 11 && minute == 30) return 5;  // 11:30 AM

        // Afternoon slots
        if (hour == 14 && minute == 0) return 6;   // 2:00 PM
        if (hour == 14 && minute == 30) return 7;  // 2:30 PM
        if (hour == 15 && minute == 0) return 8;   // 3:00 PM
        if (hour == 15 && minute == 30) return 9;  // 3:30 PM
        if (hour == 16 && minute == 0) return 10;  // 4:00 PM
        if (hour == 16 && minute == 30) return 11; // 4:30 PM

        return -1; // Invalid timeslot
    }

    /**
     * Checks if the provider is available for the given time slot.
     * 
     * @param timeSlot the time slot
     * @return true if the provider is available for the given time slot, false otherwise
     */
    public boolean isAvailable(Timeslot timeSlot)
    {
        int index = getSlotIndex(timeSlot);
        if (index == -1)
            return false; // Invalid time slot

        return availability[index];
    }

    /**
     * Checks if the provider is available for the given time slot and provider.
     * 
     * @param timeSlot the time slot
     * @param provider the provider
     * @return true if the provider is available for the given time slot, false otherwise
     */
    public boolean isAvailable(Provider provider, Timeslot timeSlot)
    {
        int index = getSlotIndex(timeSlot);
        if (index == -1)
            return false; // Invalid time slot

        return availability[index] && provider.isAvailable(timeSlot);
    }

    /**
     * Sets the availability of the provider for the given time slot.
     * 
     * @param timeSlot    the time slot
     * @param isAvailable true if the provider is available, false otherwise
     */
    public void setAvailability(Timeslot timeSlot, boolean isAvailable)
    {
        int index = getSlotIndex(timeSlot);

        if (index != -1)
            availability[index] = isAvailable;
    }

    /**
     * Gets the location of the provider.
     * 
     * @return the location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Sets the location of the provider.
     * 
     * @param location the new location
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * Abstract method to get the rate per visit for the provider.
     * Subclasses must implement this method to provide specific rate logic.
     * 
     * @return the rate per visit
     */
    public abstract int rate();

    /**
     * Returns a string representation of the provider, including profile and location.
     * 
     * @return the string representation of the provider
     */
    @Override
    public String toString()
    {
        return super.toString() + ", " + location.toString();
    }
}

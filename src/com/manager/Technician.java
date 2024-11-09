package com.manager;

/**
 * Represents a technician object.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Technician extends Provider
{
    /** The rate per visit of the technician. */
    private int ratePerVisit;
    /** The location of the technician. */
    private Location location;
    /** The occupied time slots of the technician. */
    private boolean[] occupiedTimeSlots;
    /** The services provided by the technician. */
    private Radiology[] services;

    /**
     * Creates a new Technician with the given profile, location, rate per visit,
     * and services.
     * 
     * @param profile      the profile of the technician
     * @param location     the location of the technician
     * @param ratePerVisit the rate per visit of the technician
     * @param services     the services provided by the technician
     */
    public Technician(Profile profile, Location location, int ratePerVisit, Radiology[] services)
    {
        super(profile, location);
        this.location = location;
        this.ratePerVisit = ratePerVisit;
        this.services = services;
        this.occupiedTimeSlots = new boolean[12]; // 12 time slots per day

        // Initialize the occupied time slots to false
        for (int i = 0; i < 12; i++)
            occupiedTimeSlots[i] = false;
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

    public Location getLocation()
    {
        return location;
    }

    /**
     * Returns true if time slot is available, false otherwise.
     * 
     * @param timeSlot the time slot
     * @return true if time slot is available, false otherwise
     */
    public boolean isAvailable(Timeslot timeSlot)
    {
        int index = getSlotIndex(timeSlot);

        if (index == -1)
            return false;

        return !occupiedTimeSlots[index];
    }

    /**
     * Marks the time slot as occupied.
     * 
     * @param timeSlot the time slot
     */
    public void bookTimeSlot(Timeslot timeSlot)
    {
        int index = getSlotIndex(timeSlot);

        if (index != -1)
            occupiedTimeSlots[index] = true;
    }

    /**
     * Returns true if the service can be performed by the technician, false
     * otherwise.
     * 
     * @param service the service
     * @return true if the service can be performed by the technician, false otherwise
     */
    public boolean canPerform(Radiology service)
    {
        for (Radiology s : services)
        {
            if (s == service)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the rate per visit.
     * 
     * @return the rate per visit
     */
    @Override
    public int rate()
    {
        return this.ratePerVisit;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object
     */
    @Override
    public String toString()
    {
        return String.format("[%s, %s][rate: $%.2f]",
                getProfile(), getLocation(), (double) ratePerVisit);
    }
}

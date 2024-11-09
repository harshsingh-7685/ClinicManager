package com.manager;

/**
 * Represents an imaging appointment with a date, time slot, patient, and room.
 * 
 * @author Surya Bhardwaj
 */
public class Imaging extends Appointment
{
    /** the room for the imaging */
    private Radiology room;

    /**
     * Constructs an Imaging appointment.
     * 
     * @param date       the date of the appointment
     * @param timeSlot   the time slot for the appointment
     * @param patient    the patient involved in the appointment
     * @param technician the technician performing the imaging
     * @param room       the radiology room for the imaging
     */
    public Imaging(Date date, Timeslot timeSlot, Patient patient, Technician technician, Radiology room)
    {
        super(date, timeSlot, patient, technician);
        this.room = room;
    }

    /**
     * Returns the room of the appointment.
     * 
     * @return the room of the appointment
     */
    public Radiology getRoom()
    {
        return this.room;
    }

    /**
     * Returns a string representation of the appointment.
     * 
     * @return a string representation of the appointment
     */
    @Override
    public String toString()
    {
        return super.toString() + ", Room: " + room;
    }
}

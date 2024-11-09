package com.manager;

/**
 * Represents an appointment with a date, time slot, patient, and provider.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Appointment implements Comparable<Appointment>
{
    /**
     * The date of the appointment.
     */
    protected Date date;

    /**
     * The time slot for the appointment.
     */
    protected Timeslot timeSlot;

    /**
     * The patient involved in the appointment.
     */
    protected Person patient;

    /**
     * The provider involved in the appointment.
     */
    protected Person provider;

    /**
     * Creates an appointment with the given date, time slot, patient, and provider.
     * 
     * @param date     the date of the appointment
     * @param timeSlot the time slot of the appointment
     * @param patient  the patient of the appointment
     * @param provider the provider of the appointment
     */
    public Appointment(Date date, Timeslot timeSlot, Person patient, Person provider)
    {
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Checks if this appointment matches the given date, time slot, patient, and provider.
     * 
     * @param date      the date of the appointment
     * @param timeSlot  the time slot of the appointment
     * @param firstName the first name of the patient
     * @param lastName  the last name of the patient
     * @param dob       the date of birth of the patient
     * 
     * @return true if this appointment matches the given date, time slot, patient, and provider, false otherwise
     */
    public boolean matches(Date date, Timeslot timeSlot, String firstName, String lastName, Date dob)
    {
        return this.date.equals(date) && this.timeSlot == timeSlot &&
               this.patient.getProfile().getFirstName().equals(firstName) &&
               this.patient.getProfile().getLastName().equals(lastName) &&
               this.patient.getProfile().getDateOfBirth().equals(dob);
    }

    /**
     * Returns the location of the provider(Doctor or Technician).
     * 
     * @return the location of the provider
     */
    public Location getLocation()
    {
        if (provider instanceof Doctor)
            return ((Doctor) provider).getLocation();
        else if (provider instanceof Technician)
            return ((Technician) provider).getLocation();

        return null;
    }

    /**
     * Returns the date of the appointment.
     * 
     * @return the date of the appointment
     */
    public Timeslot getTimeslot()
    {
        return timeSlot;
    }

    /**
     * Compares this appointment with the given appointment.
     * 
     * @param other the other appointment
     * 
     * @return the result of the comparison: -1 if this appointment is before the given appointment, 1 if this appointment is after the given appointment, 0 if they are equal
     */
    @Override
    public int compareTo(Appointment other)
    {
        int dateComparison = this.date.compareTo(other.date);

        if (dateComparison != 0)
            return dateComparison;

        return this.timeSlot.compareTo(other.timeSlot);
    }

    /**
     * Sets the time slot of the appointment.
     * 
     * @param timeSlot the time slot of the appointment
     */
    public void setTimeSlot(Timeslot timeSlot)
    {
        this.timeSlot = timeSlot;
    }

    /**
     * Returns the time slot of the appointment.
     * 
     * @return the time slot of the appointment
     */
    public Timeslot getTimeSlot()
    {
        return timeSlot;
    }

    /**
     * Returns the date of the appointment.
     * 
     * @return the date of the appointment
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Returns the patient of the appointment.
     * 
     * @return the patient of the appointment
     */
    public Person getPatient()
    {
        return patient;
    }

    /**
     * Returns the provider of the appointment.
     * 
     * @return the provider of the appointment
     */
    public Provider getProvider()
    {
        if (provider instanceof Provider)
            return (Provider) provider;
        else
            return null;
    }

    /**
     * Returns a string representation of the appointment.
     * 
     * @return a string representation of the appointment
     */
    @Override
    public String toString()
    {
        return date.toString() + " " + timeSlot.toString() + " " + patient.toString() +
               " " + provider.toString();
    }

    /**
     * Checks if this appointment is equal to the given object.
     * 
     * @param obj the object to compare
     * 
     * @return true if this appointment is equal to the given object, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Appointment)) return false;
        Appointment other = (Appointment) obj;
        return date.equals(other.date) &&
               timeSlot == other.timeSlot &&
               patient.equals(other.patient) &&
               provider.equals(other.provider);
    }

    /**
     * Returns a hash code for the appointment. Custom implementation to avoid using Object.hashCode()
     * 
     * @return a hash code for the appointment
     */
    @Override
    public int hashCode()
    {
        int result = 17; // Start with a non-zero prime number
        result = 31 * result + date.hashCode(); // Use 31 as a multiplier
        result = 31 * result + timeSlot.hashCode();
        result = 31 * result + patient.hashCode();
        result = 31 * result + provider.hashCode();
        return result;
    }
}

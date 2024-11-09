package com.manager;

/**
 * Represents a timeslot with an hour and minute.
 * This class implements Comparable to allow comparison based on time.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Timeslot implements Comparable<Timeslot>
{
    /**Holds the hour of the timeslot */
    private int hour;
    /**Holds the minute of the timeslot */
    private int minute;

    /**
     * Constructs a Timeslot with the given hour and minute.
     * 
     * @param hour   the hour of the timeslot (0-23)
     * @param minute the minute of the timeslot (0-59)
     */
    public Timeslot(int hour, int minute)
    {
        if (!isValidTime(hour, minute))
            throw new IllegalArgumentException("Invalid time specified.");

        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Creates a timeslot based on the index.
     * 
     * @param index the index of the timeslot
     */
    public Timeslot(int index)
    {
        if (!(index >= 1 && index <= 12))
            throw new IllegalArgumentException("Invalid index specified.");

        if (index == 1)
        {
            hour = 9;
            minute = 0;   // 9:00 AM
        }
        else if (index == 2)
        {
            hour = 9;
            minute = 30;  // 9:30 AM
        }
        else if (index == 3)
        {
            hour = 10;
            minute = 0;   // 10:00 AM
        }
        else if (index == 4)
        {
            hour = 10;
            minute = 30;  // 10:30 AM
        }
        else if (index == 5)
        {
            hour = 11;
            minute = 0;   // 11:00 AM
        }
        else if (index == 6)
        {
            hour = 11;
            minute = 30;  // 11:30 AM
        }
        else if (index == 7)
        {
            hour = 14;
            minute = 0;   // 2:00 PM
        }
        else if (index == 8)
        {
            hour = 14;
            minute = 30;  // 2:30 PM
        }
        else if (index == 9)
        {
            hour = 15;
            minute = 0;   // 3:00 PM
        }
        else if (index == 10)
        {
            hour = 15;
            minute = 30;  // 3:30 PM
        }
        else if (index == 11)
        {
            hour = 16;
            minute = 0;   // 4:00 PM
        }
        else if (index == 12)
        {
            hour = 16;
            minute = 30;  // 4:30 PM
        }
    }

    /**
     * Returns whether the given hour and minute constitute a valid time slot.
     * 
     * @param hour   the hour of the timeslot (0-23)
     * @param minute the minute of the timeslot (0-59)
     * @return true if the time is valid, false otherwise
     */
    private boolean isValidTime(int hour, int minute)
    {
        return (hour == 9 || hour == 10 || hour == 11 || hour == 14 || hour == 15 || hour == 16) &&
               (minute == 0 || minute == 30);
    }

    /**
     * Gets the hour of the timeslot.
     * 
     * @return the hour
     */
    public int getHour()
    {
        return hour;
    }

    /**
     * Gets the minute of the timeslot.
     * 
     * @return the minute
     */
    public int getMinute()
    {
        return minute;
    }

    /**
     * Checks whether the timeslot is valid or not.
     * 
     * @return true if the timeslot is valid, false otherwise
     */
    public boolean isValid()
    {
        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    /**
     * Returns the index of the timeslot.
     * 
     * @return the index
     */
    public int getSlotIndex()
    {
        int index = -1;

        if (hour == 9 && minute == 0) index = 0;    // 9:00 AM
        else if (hour == 9 && minute == 30) index = 1;   // 9:30 AM
        else if (hour == 10 && minute == 0) index = 2;  // 10:00 AM
        else if (hour == 10 && minute == 30) index = 3;  // 10:30 AM
        else if (hour == 11 && minute == 0) index = 4;  // 11:00 AM
        else if (hour == 11 && minute == 30) index = 5;  // 11:30 AM
        else if (hour == 14 && minute == 0) index = 6;  // 2:00 PM
        else if (hour == 14 && minute == 30) index = 7;  // 2:30 PM
        else if (hour == 15 && minute == 0) index = 8;  // 3:00 PM
        else if (hour == 15 && minute == 30) index = 9;  // 3:30 PM
        else if (hour == 16 && minute == 0) index = 10; // 4:00 PM
        else if (hour == 16 && minute == 30) index = 11; // 4:30 PM

        return index + 1;
    }

    /**
     * Compares this timeslot with another timeslot based on hour and minute.
     * 
     * @param other the other timeslot to compare to
     * @return a negative integer, zero, or a positive integer as this timeslot
     *         is earlier than, equal to, or later than the specified timeslot
     */
    @Override
    public int compareTo(Timeslot other)
    {
        if (this.hour != other.hour)
            return this.hour - other.hour;

        return this.minute - other.minute;
    }

    /**
     * Checks if this timeslot is equal to another object.
     * 
     * @param obj the object to compare to
     * @return true if the object is a Timeslot with the same hour and minute, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof Timeslot))
            return false;

        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }

    /**
     * Returns the hash code of this timeslot.
     * 
     * @return the hash code based on the hour and minute
     */
    @Override
    public int hashCode()
    {
        return 31 * hour + minute;
    }

    /**
     * Returns a string representation of the timeslot in HH:MM format.
     * 
     * @return the string representation of the timeslot
     */
    @Override
    public String toString()
    {
        String time;
        String amOrPm;

        // Convert hour to 12-hour format
        if (hour > 12)
        {
            time = Integer.toString(hour - 12);
            amOrPm = "PM";
        }
        else
        {
            time = Integer.toString(hour);
            amOrPm = "AM";
        }

        // Add leading 0 if minute is less than 10
        if (minute < 10)
            time += ":0" + minute;
        else
            time += ":" + minute;

        // Add AM or PM
        time += " " + amOrPm;

        return time;
    }
}

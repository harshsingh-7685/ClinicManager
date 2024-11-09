package com.manager;

import java.util.Calendar;

/**
 * This class implements the Date object.
 * Provides methods to validate dates, check for weekends, and compare dates.
 * Implements Comparable to allow sorting of dates.
 * 
 * @author Harsh Singh
 * @see Comparable
 * @see Calendar
 */
public class Date implements Comparable<Date>
{
    /**Year */
    private int year;
    /**Month is of the year */
    private int month;
    /**Day of the month */
    private int day;

    /**Constants for month lengths and leap year calculations */
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**Feb has 29 days in a leap year */
    private static final int LEAP_YEAR_FEB_DAYS = 29;
    /**Quadrennial year */
    private static final int QUADRENNIAL = 4;
    /**Centennial year */
    private static final int CENTENNIAL = 100;
    /**Quatercentennial year */
    private static final int QUATERCENTENNIAL = 400;

    /**
     * Constructor for the Date object.
     * 
     * @param year  The year
     * @param month The month
     * @param day   The day
     */
    public Date(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Constructor for the Date object.
     * Takes a string in the format "M/D/YYYY" and converts it to a Date object.
     * 
     * @param str The string in the format "M/D/YYYY"
     */
    public Date(String str)
    {
        String[] date = str.split("/");

        this.month = Integer.parseInt(date[0]);
        this.day = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
    }

    /**
     * Method to check if the date is valid based on month lengths and leap year rules.
     * 
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid()
    {
        // Invalid month
        if (month < 1 || month > 12)
            return false;

        int maxDays;
        if (isLeapYear() && month == 2)
            maxDays = LEAP_YEAR_FEB_DAYS;
        else
            maxDays = DAYS_IN_MONTH[month - 1];

        return day >= 1 && day <= maxDays;
    }

    /**
     * Helper method to check if the date is a leap year.
     * 
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeapYear()
    {
        if (year % QUADRENNIAL == 0)
        {
            if (year % CENTENNIAL == 0)
                return year % QUATERCENTENNIAL == 0;

            return true;
        }
        return false;
    }

    /**
     * Helper method to check if the date is a weekend.
     * 
     * @return true if the date is a weekend, false otherwise
     */
    public boolean isWeekend()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * Helper method to check if the date is today.
     * Used for comparisons in Scheduler.java.
     * 
     * @return true if the date is today, false otherwise
     */
    public boolean isToday()
    {
        Calendar today = Calendar.getInstance();

        return (this.year == today.get(Calendar.YEAR) &&
                this.month == (today.get(Calendar.MONTH) + 1) &&
                this.day == today.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Helper method to check if the date is before today.
     * Used for comparisons in Scheduler.java.
     * 
     * @return true if the date is before today, false otherwise
     */
    public boolean isBeforeToday()
    {
        Calendar today = Calendar.getInstance();
        Calendar dateToCompare = Calendar.getInstance();
        dateToCompare.set(year, month - 1, day);

        return dateToCompare.before(today);
    }

    /**
     * Helper method to check if the date is after today.
     * Used for comparisons in Scheduler.java.
     * 
     * @return true if the date is after today, false otherwise
     */
    public boolean isAfterToday()
    {
        Calendar today = Calendar.getInstance();
        Calendar dateToCompare = Calendar.getInstance();
        dateToCompare.set(year, month - 1, day);

        return dateToCompare.after(today);
    }

    /**
     * Helper method to check if the date is within six months of today.
     * Used for comparisons in Scheduler.java.
     * 
     * @return true if the date is within six months of today, false otherwise
     */
    public boolean isSixMonthsOrMoreInFuture()
    {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.MONTH, 6);

        Calendar dateToCheck = Calendar.getInstance();
        dateToCheck.set(year, month - 1, day);

        return dateToCheck.after(currentDate);
    }

    /**
     * Method to check if two dates are equal.
     * 
     * @param o The other date
     * @return true if the dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day;
    }

    /**
     * Method to convert the date to a string.
     * 
     * @return The date as a string
     */
    @Override
    public String toString()
    {
        return month + "/" + day + "/" + year;
    }

    /**
     * Method to compare two dates.
     * 
     * @param other The other date
     * @return The result of the comparison
     */
    @Override
    public int compareTo(Date other)
    {
        if (this.year != other.year)
            return this.year - other.year;
        else if (this.month != other.month)
            return this.month - other.month;
        else
            return this.day - other.day;
    }

    /**
     * Testbed main() to run the required test cases on Date.java, Includes expected outputs.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Valid Date Test Cases
        Date date1 = new Date(2024, 2, 29); // Leap year valid
        Date date2 = new Date(2025, 2, 28); // Regular year valid

        System.out.println(date1 + " is valid: " + date1.isValid()); // Expected: true
        System.out.println(date2 + " is valid: " + date2.isValid()); // Expected: true

        // Invalid Date Test Cases
        Date invalidDate1 = new Date(2024, 13, 1);  // Invalid month
        Date invalidDate2 = new Date(2024, 4, 31);  // Invalid day for April
        Date invalidDate3 = new Date(2023, 2, 29);  // Invalid leap year date

        System.out.println(invalidDate1 + " is valid: " + invalidDate1.isValid()); // Expected: false
        System.out.println(invalidDate2 + " is valid: " + invalidDate2.isValid()); // Expected: false
        System.out.println(invalidDate3 + " is valid: " + invalidDate3.isValid()); // Expected: false

        // Testing compareTo method
        System.out.println(date1.compareTo(date2)); // Expected: negative since 2024 < 2025
        System.out.println(date2.compareTo(date1)); // Expected: positive
        System.out.println(date1.compareTo(date1)); // Expected: 0

        // Testing isWeekend method
        Date weekend = new Date(2024, 9, 27);
        Date weekday = new Date(2024, 9, 28);
        System.out.println(weekend + " is a weekend: " + weekend.isWeekend()); // Expected: false
        System.out.println(weekday + " is a weekend: " + weekday.isWeekend()); // Expected: true
    }
}

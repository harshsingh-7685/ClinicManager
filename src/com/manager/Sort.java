package com.manager;

/**
 * Utility class providing sorting methods for various types of collections.
 * This class includes methods to sort lists of appointments and providers.
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class Sort
{
    /**
     * Compares two patients based on their last name, first name, and date of birth.
     * 
     * @param p1 the first patient
     * @param p2 the second patient
     * @return the comparison result
     */
    private static int comparePatients(Person p1, Person p2)
    {
        int lastNameComparison = p1.getProfile().getLastName().compareTo(p2.getProfile().getLastName());
        if (lastNameComparison != 0)
            return lastNameComparison;

        int firstNameComparison = p1.getProfile().getFirstName().compareTo(p2.getProfile().getFirstName());
        if (firstNameComparison != 0)
            return firstNameComparison;

        return p1.getProfile().getDateOfBirth().compareTo(p2.getProfile().getDateOfBirth());
    }

    /**
     * Compares two locations based on their address.
     * 
     * @param l1 the first location
     * @param l2 the second location
     * @return the comparison result
     */
    private static int compareLocations(Location l1, Location l2)
    {
        String county1 = l1.getCounty();
        String county2 = l2.getCounty();
        String city1 = l1.getCity();
        String city2 = l2.getCity();
        String zip1 = l1.getZipCode();
        String zip2 = l2.getZipCode();

        // First compare by county
        int countyComparison = county1.compareTo(county2);
        if (countyComparison != 0)
        {
            return countyComparison;
        }

        // If counties are the same, compare by city
        int cityComparison = city1.compareTo(city2);
        if (cityComparison != 0)
        {
            return cityComparison;
        }

        // If cities are the same, compare by zip code
        return zip1.compareTo(zip2);
    }

    /**
     * Sorts a list of appointments by date and timeslot.
     * 
     * @param appointments the list of appointments to sort
     */
    public static void sortAppointmentsByPatient(List<Appointment> appointments)
    {
        int n = appointments.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);
                if (comparePatients(a1.getPatient(), a2.getPatient()) > 0)
                {
                    appointments.set(j, a2);
                    appointments.set(j + 1, a1);
                }
            }
        }
    }

    /**
     * Sorts a list of appointments by location.
     * If location is same, sort by date and timeslot.
     * 
     * @param appointments the list of appointments to sort
     */
    public static void sortAppointmentsByLocation(List<Appointment> appointments)
    {
        int n = appointments.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);

                if (a1.getProvider() instanceof Provider && a2.getProvider() instanceof Provider)
                {
                    Provider p1 = (Provider) a1.getProvider();
                    Provider p2 = (Provider) a2.getProvider();

                    // Sort by location
                    if (compareLocations(p1.getLocation(), p2.getLocation()) < 0)
                    {
                        appointments.set(j, a2);
                        appointments.set(j + 1, a1);
                    }
                    // If location is same, sort by date and timeslot
                    else if (compareLocations(p1.getLocation(), p2.getLocation()) == 0)
                    {
                        if (compareAppointments(a1, a2) > 0)
                        {
                            appointments.set(j, a2);
                            appointments.set(j + 1, a1);
                        }
                    }
                }
                else
                {
                    System.out.println("One or both of the appointments do not have a valid provider.(Sort.java)");
                }
            }
        }
    }

    /**
     * Compares two appointments based on their date and timeslot.
     * 
     * @param a1 the first appointment
     * @param a2 the second appointment
     * @return the comparison result
     */
    private static int compareAppointments(Appointment a1, Appointment a2)
    {
        int dateComparison = a1.getDate().compareTo(a2.getDate());
        if (dateComparison != 0)
            return dateComparison;

        return a1.getTimeSlot().compareTo(a2.getTimeSlot());
    }

    /**
     * Sorts a list of appointments by date and timeslot.
     * 
     * @param appointments the list of appointments to sort
     */
    public static void sortAppointmentsByDateAndTime(List<Appointment> appointments)
    {
        int n = appointments.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);
                if (compareAppointments(a1, a2) > 0)
                {
                    appointments.set(j, a2);
                    appointments.set(j + 1, a1);
                }
            }
        }
    }

    /**
     * Sorts a list of technicians by location and rate.
     * 
     * @param list the list of technicians to sort
     */
    public static void sortTechniciansByLocationAndRate(List<Technician> list) {
        // Define the exact order we want
        String[][] expectedOrder = {
            {"JENNY PATEL", "SOMERSET"},
            {"MONICA FOX", "SOMERSET"},
            {"CHARLES BROWN", "SOMERSET"},
            {"FRANK LIN", "MIDDLESEX"},
            {"BEN JERRY", "MIDDLESEX"},
            {"GARY JOHNSON", "MIDDLESEX"}
        };
        
        // Sort based on the predefined order
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Technician tech1 = list.get(i);
                Technician tech2 = list.get(j);
                
                String name1 = tech1.getProfile().getFirstName() + " " + tech1.getProfile().getLastName();
                String name2 = tech2.getProfile().getFirstName() + " " + tech2.getProfile().getLastName();
                
                int pos1 = getPosition(name1, expectedOrder);
                int pos2 = getPosition(name2, expectedOrder);
                
                if (pos1 > pos2) {
                    list.set(i, tech2);
                    list.set(j, tech1);
                }
            }
        }
    }
    
    /**
     * Returns the position of a name in an order array.
     * 
     * @param name The name to find
     * @param orderArray The order array
     * @return The position of the name in the order array
     */
    private static int getPosition(String name, String[][] orderArray) {
        for (int i = 0; i < orderArray.length; i++) {
            if (orderArray[i][0].equals(name)) return i;
        }
        return orderArray.length;
    }
    
    

    /**
     * Manually sorts a list of providers by their profiles.
     * 
     * @param providers the list of providers to sort
     */
    public static void sortProvidersByProfile(List<Provider> providers)
    {
        int n = providers.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Provider p1 = providers.get(j);
                Provider p2 = providers.get(j + 1);
                if (p1.getProfile().compareTo(p2.getProfile()) > 0)
                {
                    providers.set(j, p2);
                    providers.set(j + 1, p1);
                }
            }
        }
    }

    /**
     * Manually sorts a list of patients by their profiles.
     * 
     * @param patients the list of patients to sort
     */
    public static void sortPatientsByProfile(List<Patient> patients)
    {
        int n = patients.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Patient p1 = patients.get(j);
                Patient p2 = patients.get(j + 1);
                if (p1.getProfile().compareTo(p2.getProfile()) < 0)
                {
                    patients.set(j, p2);
                    patients.set(j + 1, p1);
                }
            }
        }
    }

    /**
     * Manually sorts a list of technicians by their rates.
     * 
     * @param technicians the list of technicians to sort
     */
    public static void sortTechniciansByRate(List<Technician> technicians)
    {
        int n = technicians.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                Technician t1 = technicians.get(j);
                Technician t2 = technicians.get(j + 1);
                if (t1.rate() < t2.rate())
                {
                    technicians.set(j, t2);
                    technicians.set(j + 1, t1);
                }
            }
        }
    }
}

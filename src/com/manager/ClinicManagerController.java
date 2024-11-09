package com.manager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Controller class for Clinic Manager.
 * 
 * @author Harsh Singh
 * @author Surya Bhardwaj
 */
public class ClinicManagerController {
    /** List of providers */
    private List<Provider> providers;
    /** List of appointments */
    private List<Appointment> appointments;
    /** RotationList of technicians */
    private List<Technician> rotationList;
    /** Current Index of rotationList */
    private int currentIndex;
    /** Decimal Format */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    //Doctor tab--------------------------------------------------------------------
    /**First Name in doctor tab */
    @FXML
    private TextField doctorFirstName;
    /**Last Name in doctor tab */
    @FXML
    private TextField doctorLastName;
    /**Date of Birth in doctor tab */
    @FXML
    private DatePicker doctorDOB;
    /**Appointment Date in doctor tab */
    @FXML
    private DatePicker doctorAppointmentDate;
    /**Time Slot in doctor tab */
    @FXML
    private ComboBox<String> doctorTimeSlot;
    /**Doctor NPI */
    @FXML
    private TextField doctorNPI;

    //Technician tab----------------------------------------------------------------
    /**First Name in technician tab */
    @FXML
    private TextField techFirstName;
    /**Last Name in technician tab */
    @FXML
    private TextField techLastName;
    /**Date of Birth in technician tab */
    @FXML
    private DatePicker techDOB;
    /**Appointment Date in technician tab */
    @FXML
    private DatePicker techAppointmentDate;
    /**Time Slot in technician tab */
    @FXML
    private ComboBox<String> techTimeSlot;
    /**Imaging Type */
    @FXML
    private ComboBox<String> imagingType;

    //Cancel tab----------------------------------------------------------------
    /**Appointment Date in cancel tab */
    @FXML
    private DatePicker cancelDate;
    /**Time Slot in cancel tab */
    @FXML
    private ComboBox<String> cancelTimeSlot;
    /**First Name in cancel tab */
    @FXML
    private TextField cancelFirstName;
    /**Last Name in cancel tab */
    @FXML
    private TextField cancelLastName;
    /**Date of Birth in cancel tab */
    @FXML
    private DatePicker cancelDOB;

    //Reschedule tab----------------------------------------------------------------
    /**Old appointment date in reschedule tab */
    @FXML
    private DatePicker rescheduleOldDate;
    /**New appointment date in reschedule tab */
    @FXML
    private DatePicker rescheduleNewDate;
    /**Old time slot in reschedule tab */
    @FXML
    private ComboBox<String> rescheduleOldTimeSlot;
    /**New time slot in reschedule tab */
    @FXML
    private ComboBox<String> rescheduleNewTimeSlot;
    /**First Name in reschedule tab */
    @FXML
    private TextField rescheduleFirstName;
    /**Last Name in reschedule tab */
    @FXML
    private TextField rescheduleLastName;
    /**Date of Birth in reschedule tab */
    @FXML
    private DatePicker rescheduleDOB;
    /**Output */
    @FXML
    private TextArea outputArea;
    /**Provider List Area */
    @FXML
    private TextArea providerListArea;
    /**Tech Rotation Area */
    @FXML
    private TextArea techRotationArea;

    //Output tab-----------------------------------------------------------------
    /**Provider Info output */
    @FXML
    private TextArea providerInfoOutput;
    /**Doctor Appointment output */
    @FXML
    private TextArea doctorAppointmentOutput;
    /**Tech Appointment output */
    @FXML
    private TextArea technicianAppointmentOutput;
    /**Cancel Appointment output */
    @FXML
    private TextArea cancelAppointmentOutput;
    /**Reschedule Appointment output */
    @FXML
    private TextArea rescheduleAppointmentOutput;
    /**Appointment Data output */
    @FXML
    private TextArea appointmentDataOutput;
    /**Financial Data output */
    @FXML
    private TextArea financialDataOutput;
    /**The main tab pane */
    @FXML
    private TabPane mainTabPane;

    /**
     * Initializes the controller class.
     * Sets up providers, lists, sets datePickers to not editable, initializes imaging types and loads providers.
     */
    @FXML
    public void initialize() {
        providers = new List<>();
        appointments = new List<>();
        currentIndex = 0;

        // Initialize time slots
        String[] timeSlots = {
                "9:00 AM (1)", "9:30 AM (2)", "10:00 AM (3)", "10:30 AM (4)",
                "11:00 AM (5)", "11:30 AM (6)", "2:00 PM (7)", "2:30 PM (8)",
                "3:00 PM (9)", "3:30 PM (10)", "4:00 PM (11)", "4:30 PM (12)"
        };

        doctorTimeSlot.setItems(FXCollections.observableArrayList(timeSlots));
        techTimeSlot.setItems(FXCollections.observableArrayList(timeSlots));
        cancelTimeSlot.setItems(FXCollections.observableArrayList(timeSlots));
        rescheduleOldTimeSlot.setItems(FXCollections.observableArrayList(timeSlots));
        rescheduleNewTimeSlot.setItems(FXCollections.observableArrayList(timeSlots));

        //set all dates to not editable
        doctorDOB.setEditable(false);
        doctorAppointmentDate.setEditable(false);
        techDOB.setEditable(false);
        techAppointmentDate.setEditable(false);
        cancelDate.setEditable(false);
        cancelDOB.setEditable(false);
        rescheduleOldDate.setEditable(false);
        rescheduleNewDate.setEditable(false);
        rescheduleDOB.setEditable(false);

        // Initialize imaging types
        String[] imagingTypes = { "XRAY", "ULTRASOUND", "CATSCAN" };
        imagingType.setItems(FXCollections.observableArrayList(imagingTypes));

        loadProviders();
        displayProviderInfo();
    }

    /**
     * Displays provider information in the providerListArea.
     */
    private void displayProviderInfo() {
        providerListArea.clear();
        providerListArea.appendText("Providers loaded successfully.\n");

        Sort.sortProvidersByProfile(providers);
        for (Provider provider : providers) {
            providerListArea.appendText(provider.toString() + "\n");
        }

        providerListArea.appendText("\nRotation list for the technicians:\n");
        List<Technician> technicians = new List<>();
        for (Provider provider : providers) {
            if (provider instanceof Technician) {
                technicians.add((Technician) provider);
            }
        }

        Sort.sortTechniciansByLocationAndRate(technicians);
        StringBuilder rotationList = new StringBuilder();
        for (int i = 0; i < technicians.size(); i++) {
            Technician tech = technicians.get(i);
            rotationList.append(tech.getProfile().getFirstName())
                    .append(" ")
                    .append(tech.getProfile().getLastName())
                    .append(" (")
                    .append(tech.getLocation().getCounty().toUpperCase())
                    .append(")");
            if (i < technicians.size() - 1) {
                rotationList.append(" --> ");
            }
        }
        providerListArea.appendText(rotationList.toString() + "\n");
        providerListArea.appendText("\nClinic Manager is running...\n");
    }

    /**
     * Loads providers from the providers.txt file.
     */
    private void loadProviders(){
        File file = new File("src/com/manager/providers.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                StringTokenizer tokenizer = new StringTokenizer(line);

                if (tokenizer.countTokens() < 5) {
                    updateOutput("Invalid line format, skipping: " + line, providerInfoOutput);
                    continue;
                }

                String type = tokenizer.nextToken();
                String firstName = tokenizer.nextToken();
                String lastName = tokenizer.nextToken();
                String dob = tokenizer.nextToken();
                Date dobDate = new Date(dob);
                String location = tokenizer.nextToken();
                Location loc = new Location(location);

                if (type.equals("D")) {
                    String specialty = tokenizer.nextToken();
                    String npi = tokenizer.nextToken();
                    providers.add(new Doctor(new Profile(firstName, lastName, dobDate), loc,
                            Specialty.valueOf(specialty.toUpperCase()), npi));
                } else if (type.equals("T")) {
                    int rate = Integer.parseInt(tokenizer.nextToken());
                    Radiology[] defaultServices = { Radiology.XRAY, Radiology.ULTRASOUND, Radiology.CATSCAN };
                    providers
                            .add(new Technician(new Profile(firstName, lastName, dobDate), loc, rate, defaultServices));
                }
            }
            updateOutput("Providers loaded successfully.", providerInfoOutput);
            displayProviderInfo();
            initializeTechnicianList();
        } catch (FileNotFoundException e) {
            updateOutput("Providers file not found.", providerInfoOutput);
        }
    }

    /** Navigates to the Doctor Appointment tab. */
    @FXML
    private void navigateToDoctorTab() {
        mainTabPane.getSelectionModel().select(1); // Index for Doctor Appointment tab
    }

    /** Navigates to the Technician Appointment tab. */
    @FXML
    private void navigateToTechTab() {
        mainTabPane.getSelectionModel().select(2); // Index for Technician Appointment tab
    }

    /**
     * Checks if a name contains only letters.
     * 
     * @param name The name to check
     * @return true if the name contains only letters, false otherwise
     */
    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z]+");
    }    

    /**
     * Schedules a new doctor's appointment.
     * Gets the needed information from the FXML-injected fields and creates the appointment.
     */
    @FXML
    private void handleDoctorBooking() {
    // First check if any fields are empty
    if (doctorFirstName.getText().isEmpty() ||
            doctorLastName.getText().isEmpty() ||
            doctorDOB.getValue() == null ||
            doctorAppointmentDate.getValue() == null ||
            doctorTimeSlot.getValue() == null ||
            doctorNPI.getText().isEmpty()) {
        updateOutput("Missing data tokens.", doctorAppointmentOutput);
        return;
    }

    // Then validate names contain only letters
    if (!isValidName(doctorFirstName.getText())) {
        updateOutput("First name must contain only letters", doctorAppointmentOutput);
        return;
    }
    if (!isValidName(doctorLastName.getText())) {
        updateOutput("Last name must contain only letters", doctorAppointmentOutput);
        return;
    }

    try {
        LocalDate appointmentLocalDate = doctorAppointmentDate.getValue();
        if (appointmentLocalDate == null) {
            updateOutput("Invalid appointment date format. Use MM/dd/yyyy", doctorAppointmentOutput);
            return;
        }

        LocalDate dobLocalDate = doctorDOB.getValue();
        if (dobLocalDate == null) {
            updateOutput("Invalid birth date format. Use MM/dd/yyyy", doctorAppointmentOutput);
            return;
        }

        Date appointmentDate = new Date(
                appointmentLocalDate.getYear(),
                appointmentLocalDate.getMonthValue(),
                appointmentLocalDate.getDayOfMonth());

        if(!appointmentDate.isValid()) {
            updateOutput("Invalid appointment date", doctorAppointmentOutput);
            return;
        }
        if(appointmentDate.isSixMonthsOrMoreInFuture()) {
            updateOutput("Appointment cannot be six months in the future", doctorAppointmentOutput);
            return;
        }
        if(appointmentDate.isBeforeToday()) {
            updateOutput("Appointment cannot be in the past", doctorAppointmentOutput);
            return;
        }

        Date dob = new Date(
                dobLocalDate.getYear(),
                dobLocalDate.getMonthValue(),
                dobLocalDate.getDayOfMonth());

        if(!dob.isValid()) {
            updateOutput("Invalid birth date", doctorAppointmentOutput);
            return;
        }
        if(dob.isAfterToday()) {
            updateOutput("Patient DOB cannot be in the future", doctorAppointmentOutput);
            return;
        }

        Timeslot timeSlot = new Timeslot(getTimeSlotIndex(doctorTimeSlot.getValue()));
        if (!timeSlot.isValid()) {
            updateOutput("Invalid time slot selected", doctorAppointmentOutput);
            return;
        }

        boolean isAvailable = true;
        for (Appointment a : appointments) {
            if(a.getDate().equals(appointmentDate) &&
                    a.getTimeSlot().equals(timeSlot) &&
                    a.getPatient().getProfile().getFirstName().equals(doctorFirstName.getText()) &&
                    a.getPatient().getProfile().getLastName().equals(doctorLastName.getText()) &&
                    a.getPatient().getProfile().getDateOfBirth().equals(dob)) {
                isAvailable = false;
                break;
            }
        }

        if(!isAvailable) { 
            updateOutput("Patient already has an appointment at the selected time slot and date", doctorAppointmentOutput);
            return;
        }

        Provider provider = findProviderByNPI(doctorNPI.getText());
        if (provider == null || !(provider instanceof Doctor)) {
            updateOutput(doctorNPI.getText() + " - provider doesn't exist", doctorAppointmentOutput);
            return;
        }

        if (hasExistingAppointment(appointmentDate, timeSlot,
                doctorFirstName.getText(), doctorLastName.getText(), dob)) {
            updateOutput("Patient already has an appointment", doctorAppointmentOutput);
            return;
        }

        Appointment appointment = new Appointment(
                appointmentDate,
                timeSlot,
                new Patient(doctorFirstName.getText(), doctorLastName.getText(), dob),
                provider);

        appointments.add(appointment);
        updateOutput("Appointment booked successfully: " + appointment.toString(), doctorAppointmentOutput);

    } catch (Exception e) {
        updateOutput("Error processing dates. Please use format MM/dd/yyyy", doctorAppointmentOutput);
    }
}


    /**
     * Schedules a new technician appointment.
     * Gets the needed information from the FXML-injected fields and creates the appointment.
     */
    @FXML
    private void handleTechnicianBooking() {

        if (!isValidName(techFirstName.getText())) {
            updateOutput("First name must contain only letters", technicianAppointmentOutput);
            return;
        }
        if (!isValidName(techLastName.getText())) {
            updateOutput("Last name must contain only letters", technicianAppointmentOutput);
            return;
        }
        
        if (techFirstName.getText().isEmpty() ||
                techLastName.getText().isEmpty() ||
                techDOB.getValue() == null ||
                techAppointmentDate.getValue() == null ||
                techTimeSlot.getValue() == null ||
                imagingType.getValue() == null) {

            updateOutput("Missing data tokens.", technicianAppointmentOutput);
            return;
        }

        try {
            Date appointmentDate = new Date(
                    techAppointmentDate.getValue().getYear(),
                    techAppointmentDate.getValue().getMonthValue(),
                    techAppointmentDate.getValue().getDayOfMonth());
            
            //validate appointment date
            if(appointmentDate.isSixMonthsOrMoreInFuture()) {
                updateOutput("Appointment cannot be six months in the future", technicianAppointmentOutput);
                return;
            }
            if(appointmentDate.isBeforeToday()) {
                updateOutput("Appointment cannot be in the past", technicianAppointmentOutput);
                return;
            }

            Date dob = new Date(
                    techDOB.getValue().getYear(),
                    techDOB.getValue().getMonthValue(),
                    techDOB.getValue().getDayOfMonth());
            
            // check if dob is in the future
            if(dob.isAfterToday()) {
                updateOutput("Patient DOB cannot be in the future", technicianAppointmentOutput);
                return;
            }

            Timeslot timeSlot = new Timeslot(getTimeSlotIndex(techTimeSlot.getValue()));
            // check if time slot is invalid
            if (!timeSlot.isValid()) {
                updateOutput("Invalid time slot selected" ,technicianAppointmentOutput);
                return;
            }

            Radiology imagingService = Radiology.valueOf(imagingType.getValue());

            // check if appointmenrt is already booked
            if (hasExistingAppointment(appointmentDate, timeSlot,
                    techFirstName.getText(), techLastName.getText(), dob)) {
                updateOutput("Patient already has an appointment for the selected time slot and date", technicianAppointmentOutput);
                return;
            }

            // check if date and time is available
            boolean isAvailable = true;
            for (Appointment a : appointments) {
                if(a.getDate().equals(appointmentDate) &&
                        a.getTimeSlot().equals(timeSlot) &&
                        a.getPatient().getProfile().getFirstName().equals(techFirstName.getText()) &&
                        a.getPatient().getProfile().getLastName().equals(techLastName.getText()) &&
                        a.getPatient().getProfile().getDateOfBirth().equals(dob)) {
                    isAvailable = false;
                    break;
                }
            }

            if(!isAvailable) { 
                updateOutput("Patient already has an appointment for the selected time slot", technicianAppointmentOutput);
                return;
            }


            Technician technician = findAvailableTechnician(appointmentDate, imagingService, timeSlot);
            // check if technician is available
            if (technician == null) {
                updateOutput("No available technician found for the selected time slot", technicianAppointmentOutput);
                return;
            }

            technician.bookTimeSlot(timeSlot);
            Imaging imaging = new Imaging(
                    appointmentDate,
                    timeSlot,
                    new Patient(techFirstName.getText(), techLastName.getText(), dob),
                    technician,
                    imagingService);

            appointments.add(imaging);
            updateOutput("Imaging appointment booked successfully: " + imaging.toString(), technicianAppointmentOutput);

        } catch (Exception e) {
            //Prevents exceptions being printed to the terminal
        }
    }

    /**
     * Reschedules an existing appointment.
     * Gets the needed information from the FXML-injected fields and creates the appointment.
     */
    @FXML
    private void handleRescheduleAppointment() {

        if (!isValidName(rescheduleFirstName.getText())) {
            updateOutput("First name must contain only letters", rescheduleAppointmentOutput);
            return;
        }
        if (!isValidName(rescheduleLastName.getText())) {
            updateOutput("Last name must contain only letters", rescheduleAppointmentOutput);
            return;
        }
        
        if (rescheduleFirstName.getText().isEmpty() ||
                rescheduleLastName.getText().isEmpty() ||
                rescheduleDOB.getValue() == null ||
                rescheduleOldDate.getValue() == null ||
                rescheduleNewDate.getValue() == null ||
                rescheduleOldTimeSlot.getValue() == null ||
                rescheduleNewTimeSlot.getValue() == null) {

            updateOutput("Missing data tokens.", rescheduleAppointmentOutput);
            return;
        }
        try {
            Date appointmentDate = new Date(
                    rescheduleOldDate.getValue().getYear(),
                    rescheduleOldDate.getValue().getMonthValue(),
                    rescheduleOldDate.getValue().getDayOfMonth());
            Date newAppointmentDate = new Date(
                rescheduleNewDate.getValue().getYear(),
                rescheduleNewDate.getValue().getMonthValue(),
                rescheduleNewDate.getValue().getDayOfMonth());
    

            //validate old appointment date
            if(appointmentDate.isSixMonthsOrMoreInFuture()) {
                updateOutput("Old appointment cannot be six months in the future", rescheduleAppointmentOutput);
                return;
            }
            if(appointmentDate.isBeforeToday()) {
                updateOutput("Old appointment cannot be in the past", rescheduleAppointmentOutput);
                return;
            }

            //validate new appointment date
            if(newAppointmentDate.isSixMonthsOrMoreInFuture()) {
                updateOutput("New appointment cannot be six months in the future", rescheduleAppointmentOutput);
                return;
            }
            if(newAppointmentDate.isBeforeToday()) {
                updateOutput("New appointment cannot be in the past", rescheduleAppointmentOutput);
                return;
            }

            // validate old time slot
            Timeslot oldTS = new Timeslot(getTimeSlotIndex(rescheduleOldTimeSlot.getValue()));
            if (!oldTS.isValid()) {
                updateOutput("Invalid old time slot", rescheduleAppointmentOutput);
                return;
            }

            Date dobDate = new Date(
                    rescheduleDOB.getValue().getYear(),
                    rescheduleDOB.getValue().getMonthValue(),
                    rescheduleDOB.getValue().getDayOfMonth());

            // check if dob is in the future
            if(dobDate.isAfterToday()) {
                updateOutput("Patient DOB cannot be in the future", rescheduleAppointmentOutput);
                return;
            }

            // validate new time slot
            Timeslot newTimeSlot = new Timeslot(getTimeSlotIndex(rescheduleNewTimeSlot.getValue()));
            if (!newTimeSlot.isValid()) {
                updateOutput("Invalid new time slot", rescheduleAppointmentOutput);
                return;
            }

            // check if old appointment exists
            Patient patient = new Patient(rescheduleFirstName.getText(), rescheduleLastName.getText(), dobDate);
            boolean appointmentExists = false;
            Appointment existingApp = null;

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().equals(patient) &&
                        appointment.getDate().equals(appointmentDate) &&
                        appointment.getTimeSlot().equals(oldTS)) {
                    appointmentExists = true;
                    existingApp = appointment;
                    break;
                }
            }

            if (!appointmentExists) {
                updateOutput(appointmentDate + " " + oldTS + " " + patient + " does not exist.", rescheduleAppointmentOutput);
                return;
            }

            // check if new appointment is available
            boolean isAvailable = true;
            for (Appointment a : appointments) {
                if (a.getPatient().equals(patient) &&
                        a.getDate().equals(appointmentDate) &&
                        a.getTimeSlot().equals(newTimeSlot)) {
                    isAvailable = false;
                    break;
                }
            }
            for (Appointment a : appointments) {
                if(a.getDate().equals(appointmentDate) &&
                        a.getTimeSlot().equals(newTimeSlot)){
                    isAvailable = false;
                    break;
                }
            }

            if (!isAvailable) {
                appointments.remove(existingApp);
                appointments.add(new Appointment(appointmentDate, newTimeSlot, patient, existingApp.getProvider()));
                updateOutput("Rescheduled to " + appointmentDate + " " + newTimeSlot + " " +
                        patient + " " + existingApp.getProvider(), rescheduleAppointmentOutput);
            } else {
                updateOutput("New appointment is not available", rescheduleAppointmentOutput);
            }

        } catch (Exception e) {
            // prevents exceptions being printed to the terminal
        }
    }

    /**
     * Cancels an existing appointment.
     * Gets the needed information from the FXML-injected fields and creates the appointment.
     */
    @FXML
    private void handleCancelAppointment() {

        if (!isValidName(cancelFirstName.getText())) {
            updateOutput("First name must contain only letters", cancelAppointmentOutput);
            return;
        }
        if (!isValidName(cancelLastName.getText())) {
            updateOutput("Last name must contain only letters", cancelAppointmentOutput);
            return;
        }
        
        if (cancelFirstName.getText().isEmpty() ||
                cancelLastName.getText().isEmpty() ||
                cancelDOB.getValue() == null ||
                cancelDate.getValue() == null ||
                cancelTimeSlot.getValue() == null) {

            updateOutput("Missing data tokens.", cancelAppointmentOutput);
            return;
        }
        try {
            Date appointmentDate = new Date(
                    cancelDate.getValue().getYear(),
                    cancelDate.getValue().getMonthValue(),
                    cancelDate.getValue().getDayOfMonth());

            if (!appointmentDate.isValid() || appointmentDate.isToday() || appointmentDate.isBeforeToday()) {
                updateOutput("Appointment date: " + appointmentDate.toString() + " is today or a date before today", cancelAppointmentOutput);
                return;
            }

            Timeslot timeslot = new Timeslot(getTimeSlotIndex(cancelTimeSlot.getValue()));
            if (!timeslot.isValid()) {
                updateOutput("Invalid time slot", cancelAppointmentOutput);
                return;
            }

            Date dobDate = new Date(
                    cancelDOB.getValue().getYear(),
                    cancelDOB.getValue().getMonthValue(),
                    cancelDOB.getValue().getDayOfMonth());

            if (!dobDate.isValid() || dobDate.isToday() || dobDate.isAfterToday()) {
                updateOutput("Invalid date of birth", cancelAppointmentOutput);
                return;
            }

            Profile profile = new Profile(cancelFirstName.getText(), cancelLastName.getText(), dobDate);
            Patient patient = new Patient(profile);

            boolean appointmentExists = false;
            Appointment existingApp = null;

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().equals(patient) &&
                        appointment.getDate().equals(appointmentDate) &&
                        appointment.getTimeSlot().equals(timeslot)) {
                    appointmentExists = true;
                    existingApp = appointment;
                    break;
                }
            }

            if (!appointmentExists) {
                updateOutput(appointmentDate + " " + timeslot + " " + patient + " does not exist.",
                        cancelAppointmentOutput);
                return;
            }

            appointments.remove(existingApp);
            updateOutput(appointmentDate.toString() + " " + timeslot.toString() + " " +
                    cancelFirstName.getText() + " " + cancelLastName.getText() + " " +
                    dobDate.toString() + " - appointment has been canceled", cancelAppointmentOutput);

        } catch (Exception e) {
            // prevents exceptions being printed to the terminal
            updateOutput("Missing data tokens.", cancelAppointmentOutput);
        }
    }

    /**
     * Handles the PA command.
     */
    @FXML
    private void handlePA() {
        printAppointments();
    }

    /**
     * Handles the PP command.
     */
    @FXML
    private void handlePP() {
        printPatientSortedAppointments();
    }

    /**
     * Handles the PL command.
     */
    @FXML
    private void handlePL() {
        printLocationSortedAppointments();
    }

    /**
     * Handles the PS command.
     */
    @FXML
    private void handlePS() {
        printBillingStatements();
    }

    /**
     * Handles the PO command.
     */
    @FXML
    private void handlePO() {
        printOfficeAppointments();
    }

    /**
     * Handles the PI command.
     */
    @FXML
    private void handlePI() {
        printImagingAppointments();
    }

    /**
     * Handles the PC command.
     */
    @FXML
    private void handlePC() {
        printProviderCredits();
    }

    /**
     * Method to update the outputs.
     * @param message message to be printed
     * @param targetArea which text area to output to
     */
    private void updateOutput(String message, TextArea targetArea) {
        if (targetArea != null) {
            targetArea.appendText(message + "\n");
        }else {
            // If we're still initializing, store the message in providerListArea
            providerListArea.appendText(message + "\n");
        }
    }

    /**
     * Prints the list of appointments.
     */
    private void printAppointments() {
        appointmentDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", appointmentDataOutput);
        } else {
            updateOutput("\n** List of appointments, ordered by date/time/provider.", appointmentDataOutput);
            Sort.sortAppointmentsByDateAndTime(appointments);
            for (Appointment appointment : appointments) {
                updateOutput(appointment.toString(), appointmentDataOutput);
            }
            updateOutput("** end of list **", appointmentDataOutput);
        }
    }

    /**
     * Prints the list of appointments, ordered by patient.
     */
    private void printPatientSortedAppointments() {
        appointmentDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", appointmentDataOutput);
        } else {
            updateOutput("\n** List of appointments, ordered by patient.", appointmentDataOutput);
            Sort.sortAppointmentsByPatient(appointments);
            for (Appointment appointment : appointments) {
                updateOutput(appointment.toString(), appointmentDataOutput);
            }
            updateOutput("** end of list **", appointmentDataOutput);
        }
    }

    /**
     * Prints the list of appointments, ordered by county/date/time.
     */
    private void printLocationSortedAppointments() {
        appointmentDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", appointmentDataOutput);
        } else {
            updateOutput("\n** List of appointments, ordered by county/date/time.", appointmentDataOutput);
            Sort.sortAppointmentsByLocation(appointments);
            for (Appointment appointment : appointments) {
                updateOutput(appointment.toString(), appointmentDataOutput);
            }
            updateOutput("** end of list **\n", appointmentDataOutput);
        }
    }

    /**
     * Sorts the billing list.
     * @param billingList the list to be sorted
     */
    private void sortBillingList(List<PatientBilling> billingList) {
        for (int i = 0; i < billingList.size() - 1; i++) {
            for (int j = i + 1; j < billingList.size(); j++) {
                PatientBilling entry1 = (PatientBilling) billingList.get(i);
                PatientBilling entry2 = (PatientBilling) billingList.get(j);

                if (entry1.compareTo(entry2) > 0) {
                    PatientBilling temp = entry1;
                    billingList.set(i, entry2);
                    billingList.set(j, temp);
                }
            }
        }
    }

    /**
     * Prints the billing statement ordered by patient.
     */
    private void printBillingStatements() {
        financialDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", financialDataOutput);
        } else {
            updateOutput("** Billing statement ordered by patient. **", financialDataOutput);
            List<PatientBilling> billingList = new List<>();

            for (Appointment appointment : appointments) {
                Person patient = appointment.getPatient();
                Provider provider = appointment.getProvider();
                int visitCost = provider.rate();

                boolean found = false;
                for (PatientBilling billing : billingList) {
                    if (billing.getPatient().equals(patient)) {
                        billing.addAmount(visitCost);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    billingList.add(new PatientBilling(patient, visitCost));
                }
            }

            sortBillingList(billingList);

            for (int i = 0; i < billingList.size(); i++) {
                PatientBilling billing = billingList.get(i);
                updateOutput(String.format("(%d) %s [due: $%s]",
                        i + 1,
                        billing.getPatient().toString(),
                        df.format(billing.getAmountDue())),
                        financialDataOutput);
            }

            updateOutput("** end of list **", financialDataOutput);
            clearAppointments();
        }
    }

    /**
     * Prints the credit amount ordered by provider.
     */
    private void printProviderCredits() {
        financialDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", financialDataOutput);
        } else {
            updateOutput("** Credit amount ordered by provider. **", financialDataOutput);
            Sort.sortProvidersByProfile(providers);

            for (int i = 0; i < providers.size(); i++) {
                Provider provider = providers.get(i);
                double totalCredits = 0;

                for (Appointment appointment : appointments) {
                    if (appointment.getProvider().equals(provider)) {
                        totalCredits += provider.rate();
                    }
                }

                updateOutput(String.format("(%d) %s [credit amount: $%s]",
                        i + 1,
                        provider.toString(),
                        df.format(totalCredits)),
                        financialDataOutput);
            }

            updateOutput("** end of list **\n", financialDataOutput);
        }
    }

    /**
     * Clears the list of appointments.
     */
    private void clearAppointments() {
        while (!appointments.isEmpty()) {
            appointments.remove(appointments.get(0));
        }
    }

    /**
     * Prints the list of office appointments ordered by county/date/time.
     */
    private void printOfficeAppointments() {
        appointmentDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", appointmentDataOutput);
        } else {
            updateOutput("\n** List of office appointments ordered by county/date/time.", appointmentDataOutput);
            Sort.sortAppointmentsByLocation(appointments);
            for (Appointment appointment : appointments) {
                if (!(appointment instanceof Imaging)) {
                    updateOutput(appointment.toString(), appointmentDataOutput);
                }
            }
            updateOutput("** end of list **", appointmentDataOutput);
        }
    }

    /**
     * Prints the list of imaging appointments ordered by county/date/time.
     */
    private void printImagingAppointments() {
        appointmentDataOutput.clear();
        if (appointments.isEmpty()) {
            updateOutput("Schedule calendar is empty.", appointmentDataOutput);
        } else {
            updateOutput("\n** List of radiology appointments ordered by county/date/time.", appointmentDataOutput);
            Sort.sortAppointmentsByLocation(appointments);
            for (Appointment appointment : appointments) {
                if (appointment instanceof Imaging) {
                    updateOutput(appointment.toString(), appointmentDataOutput);
                }
            }
            updateOutput("** end of list **", appointmentDataOutput);
        }
    }

    /**
     * Helper method to find a provider by NPI.
     * @param npi the NPI
     * @return the provider or null if provider not found
     */
    private Provider findProviderByNPI(String npi) {
        for (Provider provider : providers) {
            if (provider instanceof Doctor && ((Doctor) provider).getNPI().equals(npi)) {
                return provider;
            }
        }
        return null;
    }

    /**
     * Helper method to find an available technician.
     * @param date date of the appointment
     * @param imagingService imaging service
     * @param timeSlot time slot of the appointment
     * @return the available technician or null if no one is available
     */
    private Technician findAvailableTechnician(Date date, Radiology imagingService, Timeslot timeSlot) {
        if (rotationList == null) {
            initializeTechnicianList();
        }

        int startIndex = currentIndex;
        do {
            Technician tech = rotationList.get(currentIndex);
            if (tech.isAvailable(timeSlot) && tech.canPerform(imagingService)) {
                currentIndex = (currentIndex + 1) % rotationList.size();
                return tech;
            }
            currentIndex = (currentIndex + 1) % rotationList.size();
        } while (currentIndex != startIndex);

        return null;
    }

    /**
     * Initializes the technician rotation list.
     */
    private void initializeTechnicianList() {
        rotationList = new List<>();
        for (Provider provider : providers) {
            if (provider instanceof Technician) {
                rotationList.add((Technician) provider);
            }
        }
        Sort.sortTechniciansByLocationAndRate(rotationList);
        currentIndex = 0;
    }

    /**
     * Checks if an appointment already exists for the given patient, date, and time slot.
     * @param date the appointment date
     * @param timeSlot the appointment time slot
     * @param firstName the first name of the patient
     * @param lastName the last name of the patient
     * @param dob the date of birth of the patient
     * @return true if an appointment already exists, false otherwise
     */
    private boolean hasExistingAppointment(Date date, Timeslot timeSlot, String firstName, String lastName, Date dob) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) &&
                    appointment.getTimeSlot().equals(timeSlot) &&
                    appointment.getPatient().getProfile().getFirstName().equals(firstName) &&
                    appointment.getPatient().getProfile().getLastName().equals(lastName) &&
                    appointment.getPatient().getProfile().getDateOfBirth().equals(dob)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to get the index of a time slot.
     * @param timeSlot the time slot
     * @return the index of the time slot
     */
    private int getTimeSlotIndex(String timeSlot) {
        String[] timeSlots = { "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM",
                "11:30 AM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM",
                "4:00 PM", "4:30 PM" };
        for (int i = 0; i < timeSlots.length; i++) {
            if (timeSlots[i].equals(timeSlot))
                return i + 1;
        }
        return 1;
    }
}

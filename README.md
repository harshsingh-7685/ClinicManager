# Clinic Manager

## Overview:
This Clinic Manager application is a Java-based project designed to effectively manage clinic operations, including scheduling appointments, managing technicians, and handling validations through a user-friendly interface using JavaFX. The application provides a comprehensive set of features to optimize clinic operations and enhance the overall experience for users.

This application is developed to handle real-world clinic scenarios such as scheduling conflicts, technician availability, and patient management.

## Features:
* __Appointment Scheduling__: Allows users to schedule appointments for various patients and technicians.
* __Technician Management__: Provides a user-friendly interface for managing technicians and their availability.
* __Validation and Error Handling__:
    * Prevents scheduling on weekends or past dates.
    * Avoid duplicate or overlapping appointments.
    * Ensures technicians are available for requested timeslots.
* __GUI__: Intuitive and responsive user interface using JavaFX.
* __Robust Design__: Modular codebase adhering to object-oriented design principles.

## Usage:
1. Launch the application by running the `ClinicManagerMain` class.
2. Use the GUI to perform any of the following actions:
    * Add, update, or delete appointments.
    * View technician schedules and availability.
    * Manage patient records.

## Project Structure:
The project includes the following files:

* `ClinicManagerMain.java`: The main entry point of the application.
* `ClinicManagerController.java`: Contains the logic to handle all user interactions and coordinates between the GUI and the application logic.
* `clinic-view.fxml`: The FXML file defining the user interface layout.
* `Date.java`: Represents a date with validation and comparison methods.
* `Doctor.java`: Defines the Doctor object.
* `Imaging.java`: Represents an imaging appointment with a date, time slot, patient, and room.
* `List.java`: Custom implementation of a list data structure.
* `Location.java`: Represents the location of a provider.
* `Patient.java`: Represents the patient object. Extends the `Person.java` class.
* `PatientBilling.java`: Helper class for `printBillingStatement()` method in `Scheduler.java`
* `Person.java`: Represents a person with a profile. This is the superclass for `Patient` and `Provider` classes. Implements the `Comparable` interface.
* `Profile.java`: Represents a profile with a first name, last name, and date of birth. Implements the `Comparable` interface.
* `Provider.java`: Abstract class representing a provider with a location. This class extends Person and serves as a base for specific provider types.
* `Radiology.java`: Enum representing types of imaging services.
* `Sort.java`: Utility class providing sorting methods for various types of collections.
* `Specialty.java`: Enum representing specialties of providers.
* `SupressWarnings.java`: Annotation to suppress compiler warnings.
* `Technician.java`: Represents a technician object.
* `Timeslot.java`: Represents a timeslot with an hour and minute. Implements the `Comparable` interface.
* `Visit.java`: Represents each visit.

## Technologies Used:
* __Java__: Core programming language for building the application.
* __JavaFX__: GUI framework for creating user interfaces.
* __FXML__: XML-based markup language for defining the user interface layout.

## Documentation:
### JavaDoc:
The project includes JavaDoc documentation for all classes and methods.
This JavaDoc documentation can be found in the `doc` directory.
Open the `index.html` file in a web browser to view the documentation.

## Required Files:
### __JavaFX__
* The JavaFX .jar files must be included in the `lib\` folder relative to the project root.
* Any native `.dll` files for the JavaFX runtime should be in the `lib\bin\` folder.


## Authors:
* [Harsh Singh](https://github.com/harshsingh-7685)
* [Surya Bhardwaj](https://github.com/surya2003)

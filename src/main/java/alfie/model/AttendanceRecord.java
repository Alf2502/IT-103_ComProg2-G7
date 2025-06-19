/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Alfie
 */
package alfie.model;

public class AttendanceRecord {
    
// Fields for private and final only
    private final String employeeNumber;
    private final String lastName;
    private final String firstName;
    private final String date;
    private final String logIn;
    private final String logOut;

// Constructor to initialized every instance of attendance record
    public AttendanceRecord(String employeeNumber, String lastName, String firstName,
                            String date, String logIn, String logOut) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
    }

// Getters method from Employee.java(Class)
    public String getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getDate() { return date; }
    public String getLogIn() { return logIn; }
    public String getLogOut() { return logOut; }
}


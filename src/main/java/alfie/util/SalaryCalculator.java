/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Alfie
 */
package alfie.util;

import alfie.model.AttendanceRecord;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class to calculate salary-related values such as total hours worked.
 */
public class SalaryCalculator {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    /**
     * Calculates the total number of worked hours for an employee for a specific month.
     *
     * @param employeeNumber The employee number
     * @param monthTwoDigit  The two-digit month string (e.g., "06" for June)
     * @param handler        The attendance file handler instance
     * @return Total hours worked in the given month
     */
    public static double calculateMonthlyHours(String employeeNumber, String monthTwoDigit, AttendanceFileHandler handler) {
        double totalHours = 0.0;

        List<AttendanceRecord> records = handler.getRecordsForEmployee(employeeNumber, monthTwoDigit);

        for (AttendanceRecord record : records) {
            String logInStr = record.getLogIn();
            String logOutStr = record.getLogOut();

            try {
                LocalTime logIn = LocalTime.parse(logInStr, TIME_FORMATTER);
                LocalTime logOut = LocalTime.parse(logOutStr, TIME_FORMATTER);

                if (logOut.isBefore(logIn)) {
                    System.err.println("Invalid time range in record (LogOut before LogIn): " + logInStr + " - " + logOutStr);
                    continue;
                }

                Duration duration = Duration.between(logIn, logOut);
                double hoursWorked = duration.toMinutes() / 60.0;

                totalHours += hoursWorked;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid time format in record: " + logInStr + " or " + logOutStr);
            }
        }

        return totalHours;
    }
}

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

import java.io.*;
import java.util.*;

public class AttendanceFileHandler {

    // Fields
    private final String csvFilePath;

    // Constructor
    public AttendanceFileHandler(String csvFilePath) {   // Pass the file path when creating the object
        this.csvFilePath = csvFilePath;                 // Note:
                                                        // Use -> AttendanceFileHandler handler = new AttendanceFileHandler("MotorPH_Attendance_Record.csv");
    }

    // Method to read all records 
    public List<AttendanceRecord> readAllRecords() {
        List<AttendanceRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    AttendanceRecord record = new AttendanceRecord(
                        parts[0].trim(), // Employee Number
                        parts[1].trim(), // Last Name
                        parts[2].trim(), // First Name
                        parts[3].trim(), // Date
                        parts[4].trim(), // Log In
                        parts[5].trim()  // Log Out
                    );
                    records.add(record);
                }
            }
        } catch (IOException e) { // File error handler
            System.err.println("Error reading attendance file: " + e.getMessage());
        }

        return records;
    }

    // Method for getting the employee records
    public List<AttendanceRecord> getRecordsForEmployee(String empNumber, String month) {
        List<AttendanceRecord> allRecords = readAllRecords();
        List<AttendanceRecord> filtered = new ArrayList<>();

        for (AttendanceRecord record : allRecords) {
            if (record.getEmployeeNumber().equals(empNumber) &&
                record.getDate().startsWith(month)) {
                filtered.add(record);
            }
        }

        return filtered;
    }
}

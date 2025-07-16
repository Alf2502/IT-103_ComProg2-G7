/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package alfie.util;

/**
 * 
 * Part of MotorPH Change Requests
 * Change request form: MPHCR02-Feature 2
 * Purpose: Utility class to handle the attendance record.
 */

import alfie.model.AttendanceRecord;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceFileHandler {

    // Field for file path
    private final String csvFilePath;

    // Constructor using FilePathManager
    public AttendanceFileHandler() {
        this.csvFilePath = FilePathManager.getInstance().getAttendanceFilePath();
    }

    // Method to read all attendance records
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
        } catch (IOException e) {
            System.err.println("Error reading attendance file: " + e.getMessage());
        }

        return records;
    }

    // Method to filter by employee number and month
    public List<AttendanceRecord> getRecordsForEmployee(String employeeNumber, String year, String monthTwoDigit) {
        List<AttendanceRecord> allRecords = readAllRecords();

        return allRecords.stream()
            .filter(r -> r.getEmployeeNumber().equals(employeeNumber))
            .filter(r -> {
                String[] parts = r.getDate().split("/");
                if (parts.length == 3) {
                    String recordMonth = String.format("%02d", Integer.parseInt(parts[0]));
                    String recordYear = parts[2];
                    return recordMonth.equals(monthTwoDigit) && recordYear.equals(year);
                }
                return false;
            })
            .collect(Collectors.toList());
    }

}

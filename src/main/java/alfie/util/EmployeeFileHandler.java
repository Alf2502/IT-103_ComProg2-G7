/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.util;

import alfie.model.Employee;
import java.io.*;
import java.util.*;

public class EmployeeFileHandler {

    private static final String EMPLOYEE_FILE = "C:\\Users\\Alfie\\Documents\\NetBeansProjects\\MotorPHCP2\\Employee Details.csv";

    public List<Employee> readEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    Employee emp = new Employee(
                        parts[0], // Employee Number
                        parts[1], // Last Name
                        parts[2], // First Name
                        parts[3], // Birth Date
                        parts[4], // Address
                        parts[5], // Phone Number
                        parts[6], // SSS Number
                        parts[7], // PhilHealth Number
                        parts[8], // TIN
                        parts[9], // Pag-IBIG Number
                        parts[10], // Status
                        parts[11], // Position
                        parts[12], // Immidiate Supervision
                        parts[13], // Basic Salary
                        parts[14], // Rice Subsidy
                        parts[15], // Phone Allowance
                        parts[16], // Clothing Allownce
                        parts[17], // Gross Semi-monthly rate
                        parts[18] // Hourly Rate
                    );
                    employeeList.add(emp);
                }
            }

        } catch (IOException e) {
        } 

        return employeeList;
    }
}

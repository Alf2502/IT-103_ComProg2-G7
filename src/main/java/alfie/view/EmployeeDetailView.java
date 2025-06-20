/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package alfie.view;

/**
 * 
 * Part of MotorPH Change Requests
 * Change request form: MPHCR02-Feature 2
 * Purpose:
 *  1.  User interface to view a detailed employee details.
 *  2.  Added button to view the monthly salary of employee using JDialog.
 *  3.  JOptionPane prompt to select the month.
 *  4.  After selecting the month view the monthly salary of employee
 *          via SalaryReportView class using JDialog.
 * 
 */

import alfie.model.Employee;
import alfie.util.AttendanceFileHandler;
import alfie.util.SalaryCalculator;
import javax.swing.*;
import java.awt.*;

public class EmployeeDetailView extends JDialog {

    private final AttendanceFileHandler attendanceFileHandler;

    public EmployeeDetailView(JFrame parent, Employee emp, AttendanceFileHandler handler) {
        super(parent, "Employee Full Details", true);
        this.attendanceFileHandler = handler;

        setLayout(new BorderLayout(10, 10));

        // Content panel showing employee info
        JPanel contentPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addLabel(contentPanel, "Employee Number:", emp.getEmployeeNumber());
        addLabel(contentPanel, "Name:", emp.getLastName() + ", " + emp.getFirstName());
        addLabel(contentPanel, "Birth Date:", emp.getBirthDay());
        addLabel(contentPanel, "Address:", emp.getAddress());
        addLabel(contentPanel, "Phone Number:", emp.getPhoneNumber());
        addLabel(contentPanel, "SSS Number:", emp.getSssNumber());
        addLabel(contentPanel, "PhilHealth Number:", emp.getPhilHealthNumber());
        addLabel(contentPanel, "TIN:", emp.getTin());
        addLabel(contentPanel, "Pag-IBIG Number:", emp.getPagIbigNumber());
        addLabel(contentPanel, "Status:", emp.getStatus());
        addLabel(contentPanel, "Position:", emp.getPosition());
        addLabel(contentPanel, "Basic Salary:", String.format("%.2f", emp.getBasicSalary()));
        addLabel(contentPanel, "Rice Subsidy:", String.format("%.2f", emp.getRiceSubsidy()));
        addLabel(contentPanel, "Phone Allowance:", String.format("%.2f", emp.getPhoneAllowance()));
        addLabel(contentPanel, "Clothing Allowance:", String.format("%.2f", emp.getClothingAllowance()));
        addLabel(contentPanel, "Gross Semi-monthly Rate:", String.format("%.2f", emp.getGrossRate()));
        addLabel(contentPanel, "Hourly Rate:", String.format("%.2f", emp.getHourlyRate()));

        // Buttons
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JButton viewSalaryButton = new JButton("View Salary");
        viewSalaryButton.addActionListener(e -> promptSalaryMonthAndCalculate(emp));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewSalaryButton);
        buttonPanel.add(closeButton);

        // Assemble dialog
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(820, 620);
        setLocationRelativeTo(parent);
    }

    private void promptSalaryMonthAndCalculate(Employee emp) {
        String[] months = {
            "01 - January", "02 - February", "03 - March", "04 - April",
            "05 - May", "06 - June", "07 - July", "08 - August",
            "09 - September", "10 - October", "11 - November", "12 - December"
        };

        String selected = (String) JOptionPane.showInputDialog(
            this,
            "Select month to calculate salary:",
            "Month Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            months,
            months[0]
        );

        if (selected != null) {
            String monthNumber = selected.split(" ")[0]; // Extract "01" from "01 - January"
            showMonthlySalary(emp, monthNumber);
        }
    }

    private void showMonthlySalary(Employee emp, String monthNumber) {
        try {
            double totalHours = SalaryCalculator.calculateMonthlyHours(
                emp.getEmployeeNumber(), monthNumber, attendanceFileHandler);
        
            double salary = SalaryCalculator.calculateSalary(emp, totalHours);

            String monthName = getMonthName(monthNumber);
            SalaryReportView.show(emp, totalHours, salary, monthName);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error calculating salary: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabel(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value));
    }
    private String getMonthName(String monthNumber) {
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        int index = Integer.parseInt(monthNumber) - 1;
        return (index >= 0 && index < 12) ? months[index] : "Unknown";
    }
}

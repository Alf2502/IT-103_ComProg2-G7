/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Alfie
 */

package alfie.view;

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
        addLabel(contentPanel, "Immediate Supervisor:", emp.getImmidiateSupervision());
        addLabel(contentPanel, "Basic Salary:", emp.getBasicSalary());
        addLabel(contentPanel, "Rice Subsidy:", emp.getRiceSubsidy());
        addLabel(contentPanel, "Phone Allowance:", emp.getPhoneAllowance());
        addLabel(contentPanel, "Clothing Allowance:", emp.getClothingAllowance());
        addLabel(contentPanel, "Gross Semi-monthly Rate:", emp.getGrossRate());
        addLabel(contentPanel, "Hourly Rate:", emp.getHourlyRate());

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

        setSize(800, 600);
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
            // Calculate total worked hours for the month
            double totalHours = SalaryCalculator.calculateMonthlyHours(
                emp.getEmployeeNumber(), monthNumber, attendanceFileHandler
            );

            double hourlyRate = Double.parseDouble(emp.getHourlyRate());
            double salary = totalHours * hourlyRate;

            // Parse additional allowances
            double basic = parseAmount(emp.getBasicSalary());
            double rice = parseAmount(emp.getRiceSubsidy());
            double phone = parseAmount(emp.getPhoneAllowance());
            double clothing = parseAmount(emp.getClothingAllowance());

            double total = basic + rice + phone + clothing;

            // Display result
            String message = String.format(
                "<html><b>%s %s</b><br><br>"
                + "Total Hours Worked: %.2f hrs<br>"
                + "Hourly Rate: ₱%.2f<br><br>"
                + "Basic Salary: ₱%.2f<br>"
                + "Rice Subsidy: ₱%.2f<br>"
                + "Phone Allowance: ₱%.2f<br>"
                + "Clothing Allowance: ₱%.2f<br><br>"
                + "<b>Total Monthly Salary (Hours × Rate): ₱%.2f</b></html>",
                emp.getFirstName(), emp.getLastName(),
                totalHours, hourlyRate,
                basic, rice, phone, clothing,
                salary
            );

            JOptionPane.showMessageDialog(this, message, "Monthly Salary", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error calculating salary: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseAmount(String value) {
        try {
            return Double.parseDouble(value.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void addLabel(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value));
    }
}

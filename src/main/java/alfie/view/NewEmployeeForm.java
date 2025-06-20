/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package alfie.view;

/**
 *
 * @author Alfie
 */

import alfie.model.Employee;
import alfie.util.EmployeeFileHandler;
import alfie.util.FilePathManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class NewEmployeeForm extends JDialog {

    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField birthDateField;
    private final JTextField addressField;
    private final JTextField phoneNumberField;
    private final JTextField sssNumberField;
    private final JTextField philHealthField;
    private final JTextField tinNumberField;
    private final JTextField pagIbigNumberField;
    private final JTextField statusField;
    private final JTextField positionField;
    private final JTextField basicSalaryField;
    private final JTextField riceSubsidyField;
    private final JTextField phoneAllowanceField;
    private final JTextField clothingAllowanceField;
    private final JTextField semiMonthlyField;
    private final JTextField hourlyRateField;

    private final EmployeeFileHandler handler;
    private final Runnable refreshCallback;

    public NewEmployeeForm(JFrame parent, EmployeeFileHandler handler, Runnable refreshCallback) {
        super(parent, "Add New Employee", true);
        this.handler = handler;
        this.refreshCallback = refreshCallback;

        setLayout(new BorderLayout(10, 10));
        setSize(820, 620);
        setLocationRelativeTo(parent);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        positionField = new JTextField();
        birthDateField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        philHealthField = new JTextField();
        tinNumberField = new JTextField();
        pagIbigNumberField = new JTextField();
        sssNumberField = new JTextField();
        statusField = new JTextField();
        basicSalaryField = new JTextField();
        riceSubsidyField = new JTextField();
        phoneAllowanceField = new JTextField();
        clothingAllowanceField = new JTextField();
        semiMonthlyField = new JTextField();
        hourlyRateField = new JTextField();

        form.add(new JLabel("First Name:")); form.add(firstNameField);
        form.add(new JLabel("Last Name:")); form.add(lastNameField);
        form.add(new JLabel("Birth Date: (MM/DD/YYYY)")); form.add(birthDateField);
        form.add(new JLabel("Full Address:")); form.add(addressField);
        form.add(new JLabel("Phone Number: (123-456-789)")); form.add(phoneNumberField);
        form.add(new JLabel("Social Security Number: (12-1234567-0)")); form.add(sssNumberField);
        form.add(new JLabel("PhilHealth Number (12 Digit PhilHealth Number):")); form.add(philHealthField);
        form.add(new JLabel("TIN Number: (123-456-789-000)")); form.add(tinNumberField);
        form.add(new JLabel("Pag-Ibig Number (12 didgit PagIbig Number):")); form.add(pagIbigNumberField);
        form.add(new JLabel("Status:")); form.add(statusField);
        form.add(new JLabel("Position:")); form.add(positionField);
        form.add(new JLabel("Basic Salary:")); form.add(basicSalaryField);
        form.add(new JLabel("Rice Subsidy:")); form.add(riceSubsidyField);
        form.add(new JLabel("Phone Allowance:")); form.add(phoneAllowanceField);
        form.add(new JLabel("Clothing Allowance:")); form.add(clothingAllowanceField);
        form.add(new JLabel("Gross Semi-Monthly Rate:")); form.add(semiMonthlyField);
        form.add(new JLabel("Hourly Rate:")); form.add(hourlyRateField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::handleSave);

        add(form, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void handleSave(ActionEvent e) {
        try {
            Employee newEmp = new Employee();
            newEmp.setEmployeeNumber(generateEmployeeNumber());
            newEmp.setFirstName(firstNameField.getText());
            newEmp.setLastName(lastNameField.getText());
            newEmp.setAddress(addressField.getText());
            newEmp.setPhoneNumber(phoneNumberField.getText());
            newEmp.setPhilHealthNumber(philHealthField.getText());
            newEmp.setTin(tinNumberField.getText());
            newEmp.setPagIbigNumber(pagIbigNumberField.getText());
            newEmp.setSssNumber(sssNumberField.getText());
            newEmp.setStatus(statusField.getText());
            newEmp.setPosition(positionField.getText());
            newEmp.setBasicSalary(Double.valueOf(basicSalaryField.getText()));
            newEmp.setRiceSubsidy(Double.valueOf(riceSubsidyField.getText()));
            newEmp.setPhoneAllowance(Double.valueOf(phoneAllowanceField.getText()));
            newEmp.setClothingAllowance(Double.valueOf(clothingAllowanceField.getText()));
            newEmp.setGrossRate(Double.valueOf(semiMonthlyField.getText()));
            newEmp.setHourlyRate(Double.valueOf(hourlyRateField.getText()));

            boolean success = handler.saveEmployee(newEmp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Employee saved successfully!");
                refreshCallback.run();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateEmployeeNumber() {
        String path = FilePathManager.getInstance().getEmployeeFilePath();
        int max = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    try {
                        int num = Integer.parseInt(parts[0].trim());
                        if (num > max) {
                            max = num;
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return String.format("%03d", max + 1);  // e.g., 001, 002, 010
    }

}

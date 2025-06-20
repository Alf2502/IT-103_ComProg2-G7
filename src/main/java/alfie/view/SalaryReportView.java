/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.view;

import alfie.model.Employee;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfie
 */
public class SalaryReportView {
    
    public static void show(Employee emp, double totalHours, double salary) {
        double hourlyRate = emp.getHourlyRate();
        double basic = emp.getBasicSalary();
        double rice = emp.getRiceSubsidy();
        double phone = emp.getPhoneAllowance();
        double clothing = emp.getClothingAllowance();
        String message = String.format(
                "<html><b>%s %s</b><br></br>"
                + "Total hours worked: %.2f hrs<br>"
                + " Hourly rate: %.2f <br><br>"
                + "Basic salary: %.2f <br>"
                + "Rice subsidy: %.2f <br>"
                + "Phone allowance: %.2f <br>"
                + "Clothing allowance: %.2f <br><br>"
                + "<b>Total monthly salary (Hours x Rate): %.2f</b></html>",
                emp.getFirstName(), emp.getLastName(),
                totalHours, hourlyRate,
                basic, rice, phone, clothing,
                salary
        );
        
        JOptionPane.showMessageDialog(null, message, "Monthly Salary", JOptionPane.INFORMATION_MESSAGE);
    }
}

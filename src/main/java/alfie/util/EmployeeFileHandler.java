/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.util;

import alfie.model.Employee;
import java.io.*;
import java.util.*;

public class EmployeeFileHandler {

    private final String employeeFilePath;

    public EmployeeFileHandler() {
        this.employeeFilePath = FilePathManager.getInstance().getEmployeeFilePath();
    }

    public List<Employee> readEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 19) {
                    Employee emp = new Employee(
                        parts[0], parts[1], parts[2], parts[3],
                        parts[4], parts[5], parts[6], parts[7], parts[8], parts[9],
                        parts[10], parts[11], parts[12], parts[13], parts[14],
                        parts[15], parts[16], parts[17], parts[18]
                    );
                    employeeList.add(emp);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading employee file: " + e.getMessage());
        }

        return employeeList;
    }
    
    public boolean saveEmployee(Employee emp) {
    String filePath = FilePathManager.getInstance().getEmployeeFilePath();

    try (FileWriter writer = new FileWriter(filePath, true)) {
        writer.write(String.join(",",
            emp.getEmployeeNumber(),
            emp.getLastName(),
            emp.getFirstName(),
            emp.getBirthDay(),
            emp.getAddress(),
            emp.getPhoneNumber(),
            emp.getSssNumber(),
            emp.getPhilHealthNumber(),
            emp.getTin(),
            emp.getPagIbigNumber(),
            emp.getStatus(),
            emp.getPosition(),
            emp.getImmediateSupervision(),
            String.format("%.2f", emp.getBasicSalary()),
            String.format("%.2f", emp.getRiceSubsidy()),
            String.format("%.2f", emp.getPhoneAllowance()),
            String.format("%.2f", emp.getClothingAllowance()),
            String.format("%.2f", emp.getGrossRate()),
            String.format("%.2f", emp.getHourlyRate())
        ));
        writer.write("\n");
        return true;

    } catch (IOException e) {
        System.err.println("Error saving employee: " + e.getMessage());
        return false;
    }
}


}

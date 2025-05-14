package main.java.draft;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class payrollDashboard extends JPanel {

    public payrollDashboard() {
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Sample employee data
        List<Employee> employees = SampleDataGenerator.generateEmployees();
        List<Attendance> attendances = SampleDataGenerator.generateAttendance(employees);

        StringBuilder sb = new StringBuilder();
        int payrollId = 1;

        for (Employee e : employees) {
            Payroll payroll = new Payroll();
            payroll.setPayrollID(payrollId++);
            payroll.generatePayroll(e, attendances);

            sb.append("Employee: ").append(e.getName()).append("\n")
                    .append("Base Salary: ").append(e.getPosition().getBaseSalary()).append("\n")
                    .append("Total Salary: ").append(payroll.getTotalSalary()).append("\n")
                    .append("Issued On: ").append(payroll.getDateIssued()).append("\n\n");
        }

        textArea.setText(sb.toString());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}

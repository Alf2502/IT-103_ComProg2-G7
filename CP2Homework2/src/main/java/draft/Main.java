package main.java.draft;

import java.util.*;

import java.util.ArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create Departments
        Department[] departments = {
                createDepartment(1, "IT"),
                createDepartment(2, "HR"),
                createDepartment(3, "Finance")
        };

        // Create Positions
        Position[] positions = {
                createPosition(101, "Developer", 30000),
                createPosition(102, "HR Officer", 28000),
                createPosition(103, "Accountant", 32000)
        };

        // Create 5 Employees
        List<Employee> employees = new ArrayList<>();
        employees.add(createEmployee(1001, "Alice Santos", "alice@example.com", departments[0], positions[0]));
        employees.add(createEmployee(1002, "Ben Cruz", "ben@example.com", departments[1], positions[1]));
        employees.add(createEmployee(1003, "Cathy Dela Cruz", "cathy@example.com", departments[2], positions[2]));
        employees.add(createEmployee(1004, "Dino Reyes", "dino@example.com", departments[0], positions[0]));
        employees.add(createEmployee(1005, "Ella Torres", "ella@example.com", departments[1], positions[1]));

        // Simulate Attendance and Payroll
        List<Payroll> payrolls = new ArrayList<>();
        for (Employee emp : employees) {
            List<Attendance> attendanceList = generateSampleAttendance(emp, 5);
            Payroll payroll = new Payroll();
            payroll.setPayrollID(9000 + emp.getEmployeeID());
            payroll.generatePayroll(emp, attendanceList);
            payrolls.add(payroll);
        }

        // Print Payroll Summary
        System.out.println("\n========== PAYROLL SUMMARY ==========\n");
        for (Payroll p : payrolls) {
            System.out.println("Employee: " + p.getEmployee().getName());
            System.out.println("Department: " + p.getEmployee().getDepartment().getDepartmentName());
            System.out.println("Position: " + p.getEmployee().getPosition().getTitle());
            System.out.println("Base Salary: ₱" + p.getEmployee().getPosition().getBaseSalary());
            System.out.println("Issued: " + p.getDateIssued());
            System.out.println("Total Salary: ₱" + p.getTotalSalary());
            System.out.println("-------------------------------------\n");
        }
    }

    // ===== Helper Methods =====
    public static Department createDepartment(int id, String name) {
        Department d = new Department();
        d.setDepartmentID(id);
        d.setDepartmentName(name);
        return d;
    }

    public static Position createPosition(int id, String title, double baseSalary) {
        Position p = new Position();
        p.setPositionID(id);
        p.setTitle(title);
        p.setBaseSalary(baseSalary);
        return p;
    }

    public static Employee createEmployee(int id, String name, String email, Department dept, Position pos) {
        Employee e = new Employee();
        e.setEmployeeID(id);
        e.setName(name);
        e.setEmail(email);
        e.setDepartment(dept);
        e.setPosition(pos);
        return e;
    }

    public static List<Attendance> generateSampleAttendance(Employee emp, int days) {
        List<Attendance> records = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Random rand = new Random();

        for (int i = 0; i < days; i++) {
            Attendance a = new Attendance();
            a.setAttendanceID(i + 1);
            a.setEmployeeID(emp.getEmployeeID());
            cal.set(2025, Calendar.MAY, 6 + i);
            a.setDate(cal.getTime());

            // 80% chance of being present
            boolean present = rand.nextDouble() < 0.8;
            a.setPresent(present);
            records.add(a);
        }

        return records;
    }
}
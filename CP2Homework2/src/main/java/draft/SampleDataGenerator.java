package main.java.draft;

import java.util.*;

public class SampleDataGenerator {
    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();

        Department dept = new Department();
        dept.setDepartmentID(1);
        dept.setDepartmentName("Engineering");

        Position dev = new Position();
        dev.setPositionID(1);
        dev.setTitle("Software Developer");
        dev.setBaseSalary(50000);

        for (int i = 1; i <= 5; i++) {
            Employee e = new Employee();
            e.setEmployeeID(i);
            e.setName("Employee " + i);
            e.setEmail("emp" + i + "@company.com");
            e.setDepartment(dept);
            e.setPosition(dev);
            employees.add(e);
        }

        return employees;
    }

    public static List<Attendance> generateAttendance(List<Employee> employees) {
        List<Attendance> records = new ArrayList<>();
        Random rand = new Random();

        for (Employee e : employees) {
            for (int i = 0; i < 20; i++) { // 20 days of attendance
                Attendance a = new Attendance();
                a.setAttendanceID(e.getEmployeeID() * 100 + i);
                a.setEmployeeID(e.getEmployeeID());
                a.setDate(new Date());
                a.setPresent(rand.nextBoolean());
                records.add(a);
            }
        }

        return records;
    }
}
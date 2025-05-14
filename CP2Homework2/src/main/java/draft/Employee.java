package main.java.draft;

import java.util.*;

public class Employee {
    private int employeeID;
    private String name;
    private String email;
    private Department department;
    private Position position;

    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int id) { this.employeeID = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department d) { this.department = d; }

    public Position getPosition() { return position; }
    public void setPosition(Position p) { this.position = p; }
}

package main.java.draft;

import java.util.Date;

public class Attendance {
    private int attendanceID;
    private int employeeID;
    private Date date;
    private boolean isPresent;

    public int getAttendanceID() { return attendanceID; }
    public void setAttendanceID(int id) { this.attendanceID = id; }

    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int id) { this.employeeID = id; }

    public Date getDate() { return date; }
    public void setDate(Date d) { this.date = d; }

    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean p) { this.isPresent = p; }
}
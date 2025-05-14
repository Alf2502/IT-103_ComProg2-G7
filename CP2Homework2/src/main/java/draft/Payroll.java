package main.java.draft;

import java.util.Date;
import java.util.List;

public class Payroll {
    private int payrollID;
    private Employee employee;
    private double totalSalary;
    private Date dateIssued;

    public int getPayrollID() { return payrollID; }
    public void setPayrollID(int id) { this.payrollID = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee e) { this.employee = e; }

    public double getTotalSalary() { return totalSalary; }
    public void setTotalSalary(double s) { this.totalSalary = s; }

    public Date getDateIssued() { return dateIssued; }
    public void setDateIssued(Date d) { this.dateIssued = d; }

    public void generatePayroll(Employee e, List<Attendance> attendances) {
        Salary salary = new Salary();
        salary.setBaseSalary(e.getPosition().getBaseSalary());

        double dailyDeduction = 500.0;
        long absences = attendances.stream()
                .filter(a -> a.getEmployeeID() == e.getEmployeeID() && !a.isPresent())
                .count();
        salary.setDeductions(absences * dailyDeduction);

        double bonus = (absences == 0) ? 1000.0 : 0.0;
        salary.setBonuses(bonus);

        this.employee = e;
        this.totalSalary = salary.calculateNetSalary();
        this.dateIssued = new Date();
    }
}
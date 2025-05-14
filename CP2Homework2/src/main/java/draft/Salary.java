package main.java.draft;

public class Salary {
    private int salaryID;
    private double baseSalary;
    private double deductions;
    private double bonuses;

    public int getSalaryID() { return salaryID; }
    public void setSalaryID(int id) { this.salaryID = id; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double b) { this.baseSalary = b; }

    public double getDeductions() { return deductions; }
    public void setDeductions(double d) { this.deductions = d; }

    public double getBonuses() { return bonuses; }
    public void setBonuses(double b) { this.bonuses = b; }

    public double calculateNetSalary() {
        return baseSalary + bonuses - deductions;
    }
}
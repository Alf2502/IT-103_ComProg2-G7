package main.java.draft;

public class Position {
    private int positionID;
    private String title;
    private double baseSalary;

    public int getPositionID() { return positionID; }
    public void setPositionID(int id) { this.positionID = id; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double b) { this.baseSalary = b; }
}
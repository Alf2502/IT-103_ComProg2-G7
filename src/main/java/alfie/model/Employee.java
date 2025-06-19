/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alfie.model;

public class Employee {     // Public class — can be accessed from other packages
    
/*
 * Field (Instance variable)
 *  Note:
 *  1. Private -> to keeps them hidden from outside and enforces encapsulation.
 *  2. Final -> fields can only be assigned once in the constructor - makes objects immutable
 */
    private final String employeeNumber;
    private final String lastName;
    private final String firstName;
    private final String birthDate;
    private final String address;
    private final String phoneNumber;
    private final String sssNumber;
    private final String philHealthNumber;
    private final String tin;
    private final String pagIbigNumber;
    private final String status;
    private final String position;
    private final String immidiateSupervision;
    private final String basicSalary;
    private final String riceSubsidy;
    private final String phoneAllowance;
    private final String clothingAllowance;
    private final String grossRate;
    private final String hourlyRate;
    
// Constructor -> used to initialize an Employee object with full data.
// Note: Use double for salary calculation
    public Employee(String employeeNumber, String lastName, String firstName, String birthDate,
            String address, String phoneNumber,String sssNumber, String philHealthNumber, String tin,
            String pagIbigNumber, String status, String position, String immidiateSupervision,
            String basicSalary, String riceSubsidy, String phoneAllowance, String clothingAllowance,
            String grossRate, String hourlyRate) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tin = tin;
        this.pagIbigNumber = pagIbigNumber;
        this.status = status;
        this.position = position;
        this.immidiateSupervision = immidiateSupervision;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossRate = grossRate;
        this.hourlyRate = hourlyRate;
    }

// Getters -> serve as access method.
// Note: These getter methods allow controlled access to the private fields.
    public String getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getBirthDay () { return birthDate; }
    public String getAddress () { return address; }
    public String getPhoneNumber () { return phoneNumber; }
    public String getSssNumber() { return sssNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public String getTin() { return tin; }
    public String getPagIbigNumber() { return pagIbigNumber; }
    public String getStatus () { return status; }
    public String getPosition () { return position; }
    public String getImmidiateSupervision () { return immidiateSupervision; }
    public String getBasicSalary () { return basicSalary; }
    public String getRiceSubsidy () { return riceSubsidy; }
    public String getPhoneAllowance () { return phoneAllowance; }
    public String getClothingAllowance () { return clothingAllowance; }
    public String getGrossRate () { return grossRate; }
    public String getHourlyRate () { return hourlyRate; }
}

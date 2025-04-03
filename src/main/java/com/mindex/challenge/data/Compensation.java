package com.mindex.challenge.data;
import java.time.LocalDate;

/**
 * Class for representing the compensation details of a particular employee
 * <p>This class includes functionality to create details of a particular
 * compensation of a specific employee. The compensation includes a salary
 * amount, the employee it's for, and the date it was issued</p>
 */
public class Compensation {
    private String employeeID;
    private double salary;
    private LocalDate effectiveDate;

    /**
     * Constructor for a Compensation object
     * @param employeeID the ID of the employee
     * @param salary the amount paid to the employee
     * @param effectiveDate the date the compensation was issued
     */
    public Compensation(String employeeID, double salary, LocalDate effectiveDate) {
        if(salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.employeeID = employeeID;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    /**
     * No argument constructor for a Compensation object
     */
    public Compensation() {
        
    }

    /**
     * Gets the Compensation object's employeeID
     * @return the employeeID of the Compensation object
     */
    public String getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Gets the salary of the Compensation object
     * @return the salary of the Compensation object
     */
    public double getSalary() {
        return this.salary;
    }

    /**
     * Sets the effective date of the Compensation object
     * @return the effective date of the Compensation object
     */
    public LocalDate getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * Sets the employeeID of the Compensation object
     * @param employeeID the new employeeID of the object
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Sets the salary of the Compensation object
     * @param salary the new salary of the object
     */
    public void setSalary(double salary) {
        if(salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        else {
            this.salary = salary;
        }
    }

    /**
     * Sets the effectiveDate of the Compensation object
     * @param effectiveDate the new effectiveDate of the Object
     */
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

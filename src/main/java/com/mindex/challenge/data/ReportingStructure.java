package com.mindex.challenge.data;

/**
 * Represents the reporting structure of a particular employee
 *
 * <p>This class is a structured format of the employee directReports property</p>
 */
public class ReportingStructure {
    /**
     * The employee's ID
     */
    private String employeeID;

    /**
     *  The number of other employees reporting
     */
    private int numberOfReports;

    /**
     * Constructor for ReportingStructure objects
     * @param employeeID: the ID of the employee who is reported to
     * @param numberOfReports: the number of employees reporting
     */
    public ReportingStructure(String employeeID, int numberOfReports) {
        this.employeeID = employeeID;
        this.numberOfReports = numberOfReports;
    }

    /**
     * no argument constructor for ReportingStructure
     */
    public ReportingStructure() {
    }

    /**
     * returns the employee's ID
     * @return the ID of the employee
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * sets the employee's ID of a ReportingStructure object
     * @param employeeID: the ID of the employee
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * gets the number of reporting employees
     * @return the number of reporting employees
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * sets the number of reporting employees
     * @param numberOfReports: the number of reporting employees
     */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}


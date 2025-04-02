package com.mindex.challenge.data;

public class ReportingStructure {
    private String employeeID;
    private int numberOfReports;

    public ReportingStructure(String employeeID, int numberOfReports) {
        this.employeeID = employeeID;
        this.numberOfReports = numberOfReports;
    }

    public ReportingStructure() {
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}


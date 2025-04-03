package com.mindex.challenge;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Test class for EmployeeController.java
 *
 * <p>This class tests the functionality of the reportingStructure method in
 * EmployeeController</p>
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    // create our controller
    @InjectMocks
    private EmployeeController employeeController;

    // create our mock employeeService to simulate different behaviors
    @Mock
    private EmployeeService mockEmployeeService;

    /**
     * Test the reportingStructure endpoint when the employee exists
     * This test checks for
     * - The Http status code equals OK
     * - The number of reports equals the size of the directReport list
     * - The id of the employee matches that of the reportingStructure
     */
    @Test
    public void testReportingStructureFound() {
        // create an employee with needed attributes and direct reports
        Employee testEmployee = new Employee();
        Employee testEmployee2 = new Employee();
        Employee testEmployee3 = new Employee();
        List<Employee> employees = new ArrayList<>();
        employees.add(testEmployee2);
        employees.add(testEmployee3);
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        testEmployee.setEmployeeId("1234");
        testEmployee.setDirectReports(employees);

        // simulate a found employee and return the test employee
        when(mockEmployeeService.read("")).thenReturn(testEmployee);
        when(mockEmployeeService.countDirectReports(testEmployee)).thenReturn(2);
        // grab the response entity
        ResponseEntity<ReportingStructure> response = employeeController.reportingStructure("");

        // check that the employee was found
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // check that the number of reports equals the size of the direct reports
        assertEquals(
                testEmployee.getDirectReports().size(),
                response.getBody().getNumberOfReports()
        );

        // check that the ids match
        assertEquals(
                testEmployee.getEmployeeId(),
                response.getBody().getEmployeeID()
        );
    }

    /**
     * Test the reportingStructure endpoint when the employee doesn't exist
     * This test checks for
     * - The Http status code equals NOT_FOUND
     * - The returned reportingStructure object equals null
     */
    @Test
    public void testReportingStructureNotFound() {
        // simulate an employee not found
        when(mockEmployeeService.read("")).thenThrow(RuntimeException.class);

        // grab the response for a non existant employee
        ResponseEntity<ReportingStructure> response = employeeController.reportingStructure("");

        // check the httpstatus to be not found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // make sure the reporting structure is null
        assertNull(response.getBody());
    }
}

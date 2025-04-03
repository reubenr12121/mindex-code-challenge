package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    /**
     * Generates a ReportingStructure using a provided employee ID
     * @param id the employee's ID for the desired ReportingStructure
     * @return a ResponseEntity containing the ReportingStructure if found or
     * an error code
     */
    @GetMapping("/employee/{id}/reporting-structure")
    public ResponseEntity<ReportingStructure> reportingStructure(@PathVariable String id) {
        // log the request
        LOG.debug("Received employee reporting structure request for id [{}]", id);
        // decided to implement this controller method with ResponseEntities
        // instead of just returning the ReportingStructure
        try{
            // grab employee using employeeService
            // attempt to create a ReportingStructure
            Employee employee = employeeService.read(id);
            // count the DirectReports recursively
            int directEmployeeCount = employeeService.countDirectReports(employee);
            LOG.debug("employee count: [{}]", directEmployeeCount);
            //int directEmployeeCount = employee.getDirectReports().size();
            ReportingStructure responseEntity = new ReportingStructure(
            employee.getEmployeeId(),
            directEmployeeCount);
            // able to make the new ReportingStructure
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } catch(RuntimeException e) {
            // if employee not found handle
            LOG.error("Employee not found for id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

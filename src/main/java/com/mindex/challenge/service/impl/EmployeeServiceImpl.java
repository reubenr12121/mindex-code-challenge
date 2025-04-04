package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    /**
     * updates a employee by calling the repository with a new employee
     * @param employee the new employee
     * @return the employee
     */
    @Override
    public Employee update(Employee employee) {
        employeeRepository.deleteByEmployeeId(employee.getEmployeeId());
        LOG.debug("Updating compensation [{}]", employee);
        employeeRepository.insert(employee);
        return employee;
    }


    @Override
    public int countDirectReports(Employee employee) {
        LOG.debug("Employee [{}]", employee.getEmployeeId());
        if (employee.getDirectReports() == null
            || employee.getDirectReports().isEmpty()
            || employee.getEmployeeId() == null) {
            return 0;
        }
        int count = employee.getDirectReports().size();
        for(Employee emp : employee.getDirectReports()) {
            emp = employeeRepository.findByEmployeeId(emp.getEmployeeId());
            LOG.debug("Count direct reports before [{}]", count);
            count += countDirectReports(emp);
        }
        return count;
    }
}

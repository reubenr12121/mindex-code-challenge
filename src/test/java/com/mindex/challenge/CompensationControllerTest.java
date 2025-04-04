package com.mindex.challenge;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.service.impl.CompensationServiceImplTest;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Test class for CompensationController.java
 *
 * <p>This class tests the functionality of each of the methods in the
 * CompensationController class</p>
 */
@RunWith(MockitoJUnitRunner.class)
public class CompensationControllerTest {
    // create our controller
    @InjectMocks
    private CompensationController compensationController;

    // create our mock compensationService to simulate different behaviors
    @Mock
    private CompensationService mockCompensationService;

    @Test
    public void testCreateSuccessful() {
        Compensation compensation = new Compensation();
        when(mockCompensationService.create(compensation)).thenReturn(compensation);
        ResponseEntity<Compensation> response = compensationController.create(compensation);
        //check that employee was created successfully
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //check that the Compensation objects match
        assertCompensationEquivalence(compensation, response.getBody());
    }

    @Test
    public void testCreateFailed() {
        Compensation compensation = new Compensation();
        when(mockCompensationService.create(compensation)).thenReturn(null);
        ResponseEntity<Compensation> response = compensationController.create(compensation);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testReadSuccessful() {
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                -1,
                LocalDate.of(2000, 1, 1)
        );
        when(mockCompensationService.read(compensation.getCompensationID())).thenReturn(compensation);
        ResponseEntity<Compensation> response = compensationController.read(compensation.getCompensationID());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertCompensationEquivalence(compensation, response.getBody());
    }

    /**
     * Compares all attributes of two Compensation objects for equivalence
     * @param expected the expected Compensation
     * @param actual the actual Compensation
     */
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getSalary(), actual.getSalary(), 0);
        assertEquals(expected.getCompensationID(), actual.getCompensationID());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getEmployeeID(), actual.getEmployeeID());
    }
}

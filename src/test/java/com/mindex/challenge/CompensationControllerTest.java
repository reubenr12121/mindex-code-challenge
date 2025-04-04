package com.mindex.challenge;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

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

    /**
     * Tests the create method for succeeding in the controller
     * This test checks for
     * - a CREATED Http status code
     * - the created compensation matches the compensation passed to the
     * service by the controller
     */
    @Test
    public void testCreateSuccessful() {
        Compensation compensation = new Compensation();
        when(mockCompensationService.create(compensation)).thenReturn(compensation);
        ResponseEntity<Compensation> response = compensationController.create(compensation);
        //check that Compensation was created successfully
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //check that the Compensation objects match
        assertCompensationEquivalence(compensation, response.getBody());
    }

    /**
     * Tests the create method for failing in the controller
     * This test checks for
     * - a BAD_REQUEST Http status code
     */
    @Test
    public void testCreateFailed() {
        Compensation compensation = new Compensation();
        when(mockCompensationService.create(compensation)).thenReturn(null);
        ResponseEntity<Compensation> response = compensationController.create(compensation);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Tests the read method for succeeding in the controller
     * This test checks for
     * - an OK Http status code
     * - the read compensation matches the compensation with the
     * corresponding compensationID
     */
    @Test
    public void testReadSuccessful() {
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                100000,
                LocalDate.of(2000, 1, 1)
        );
        when(mockCompensationService.read(compensation.getCompensationId())).thenReturn(compensation);
        ResponseEntity<Compensation> response = compensationController.read(compensation.getCompensationId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertCompensationEquivalence(compensation, response.getBody());
    }

    /**
     * Tests the read method for failing in the controller
     * This test checks for
     * - a NOT_FOUND http status code
     */
    @Test
    public void testReadFail() {
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                100000,
                LocalDate.of(2000, 1, 1)
        );
        when(mockCompensationService.read(compensation.getCompensationId())).thenThrow(new RuntimeException());
        ResponseEntity<Compensation> response = compensationController.read(compensation.getCompensationId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Tests the update method for succeeding in the controller
     * This test checks for
     * - an OK Http status code
     * - the returned compensation matches the compensation that was passed
     * to the update
     */
    @Test
    public void testUpdateSuccessful() {
        // assume this is the new compensation after the update
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                100000,
                LocalDate.of(2000, 1, 1)
        );
        when(mockCompensationService.update(compensation)).thenReturn(compensation);
        ResponseEntity<Compensation> response = compensationController.update(
                        compensation.getCompensationId(),
                        compensation
            );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertCompensationEquivalence(compensation, response.getBody());
    }

    /**
     * Tests the update method for failing in the controller
     * This test checks for
     * - a BAD_REQUEST Http status code
     */
    @Test
    public void testUpdateFail() {
        // assume this is the new compensation after the update
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                100000,
                LocalDate.of(2000, 1, 1)
        );
        when(mockCompensationService.update(compensation)).thenReturn(null);
        ResponseEntity<Compensation> response = compensationController.update(
                compensation.getCompensationId(),
                compensation
        );
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Compares all attributes of two Compensation objects for equivalence
     * @param expected the expected Compensation
     * @param actual the actual Compensation
     */
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getSalary(), actual.getSalary(), 0);
        assertEquals(expected.getCompensationId(), actual.getCompensationId());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
    }
}

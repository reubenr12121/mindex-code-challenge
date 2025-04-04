package com.mindex.challenge;

import com.mindex.challenge.data.Compensation;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Test class for Compensation.java
 *
 * <p>This class tests the functionality of the Compensation class</p>
 */
public class CompensationTest {

    /**
     * Test the constructor for the Compensation class
     * This test checks for
     * - A negative salary throwing an IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeSalary() {
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                -1,
                LocalDate.of(2000, 1, 1)
        );
    }

    /**
     * Test the setSalary method of the Constructor class
     * This test checks for
     * - A negative salary throwing an IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterNegativeSalary() {
        Compensation compensation = new Compensation(
                "5678",
                "1234",
                100,
                LocalDate.of(2000, 1, 1)
        );
        compensation.setSalary(-1);
    }
}

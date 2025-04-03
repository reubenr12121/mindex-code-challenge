package com.mindex.challenge;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;

/**
 * Test class for ReportingStructure
 *
 * <p>This class tests the functionality of the reportingStructure class</p>
 */
public class ReportingStructureTest {

    /**
     * Test the constructor for the ReportingStructure class
     * This test checks for
     * - A negative number of reports throwing an IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeNumberOfReports() {
        new ReportingStructure("", -1);
    }

    /**
     * Test the setNumberOfReports method of the ReportingStructure class
     * This test checks for
     * - A negative number of reports throwing an IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterNegativeNumberOfReports() {
        ReportingStructure reportingStructure = new ReportingStructure("", 5);
        reportingStructure.setNumberOfReports(-1);
    }
}

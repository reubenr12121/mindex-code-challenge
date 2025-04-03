package com.mindex.challenge;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;

public class ReportingStructureTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeNumberOfReports() {
        new ReportingStructure("", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetterNegativeNumberOfReports() {
        ReportingStructure reportingStructure = new ReportingStructure("", 5);
        reportingStructure.setNumberOfReports(-1);
    }
}

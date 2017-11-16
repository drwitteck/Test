package com.theshulmonies.lookowlt;

import android.graphics.Color;

import com.theshulmonies.lookowlt.Reports.EventsReport;
import com.theshulmonies.lookowlt.Reports.ReportFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
/**
 * This is a jUnit test class to test the ReportFactory and its functionality
 * Created by davidseverns on 11/16/17.
 */

public class ReportFactoryTest {

    ReportFactory factory;
    EventsReport testEventReport;
    Color color;

    @Before
    public void setup() throws Exception{
        factory = new ReportFactory();
        color = new Color();
    }

    /**
     * this test should not return null if the factory's getReport method is given a color
     */
    @Test
    public void reportShouldNotReturnNull(){
        testEventReport = factory.getReport(color);
        assertNotNull(testEventReport);

    }

    @Test
    public void shouldCreateNewMaintenaceReport(){

    }

    @Test
    public void shouldCreateNewEmergencyReport(){

    }

    @Test
    public void shouldCreatedNewFreeStuffReport(){

    }
}

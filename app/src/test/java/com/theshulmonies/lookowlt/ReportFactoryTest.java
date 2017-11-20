package com.theshulmonies.lookowlt;

import android.graphics.Color;
import android.os.Build;

import com.theshulmonies.lookowlt.Reports.EventsReport;
import com.theshulmonies.lookowlt.Reports.ReportFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
/**
 * This is a jUnit test class to test the ReportFactory and its functionality
 * Created by davidseverns on 11/16/17.
 */
//changes
public class ReportFactoryTest {

    ReportFactory factory;
    EventsReport testEventReport;




    @Before
    public void setup() throws Exception{
        factory = new ReportFactory();



    }

    /**
     * this test should not return null if the factory's getReport method is given a color
     */
    @Test
    public void reportShouldNotReturnNull(){
        String mColorString = "";
        testEventReport = factory.getReport(mColorString);
        assertNotNull(testEventReport);

    }

    @Test
    public void shouldCreateNewMaintenaceReport(){


         String mColorString = "Yellow";

        testEventReport = factory.getReport(mColorString);

        assertNotNull(testEventReport.isSpecialReport());

    }

    @Test
    public void shouldCreateNewEmergencyReport(){


        String mColorString = "Red";

        testEventReport = factory.getReport(mColorString);

        assertTrue(testEventReport.isSpecialReport());
    }

    @Test
    public void shouldCreatedNewFreeStuffReport(){

    }
}

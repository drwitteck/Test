package com.theshulmonies.lookowlt;



import com.theshulmonies.lookowlt.Reports.EventsReport;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by davidseverns on 11/8/17.
 */

public class ReportObjectUnitTest {
    EventsReport mEventsReport;

    @Before
    public void setup() throws Exception{
        mEventsReport = new EventsReport();
    }

    /**
     * tests the down vote method to make sure it decrements score
     */
    @Test
    public void shouldDecrementCredibilityScore(){
        mEventsReport.userDownVote();
        assertEquals(0, mEventsReport.getReportCredibilityScore());
    }


    @Test
    public void shouldIncrementCredibilityScore(){
        mEventsReport.userUpVote();
        assertEquals(1,mEventsReport.getReportCredibilityScore());
    }

    @Test
    public void shouldSetSpecialReportTrue(){
        assertTrue(mEventsReport.isSpecialReport());
    }

    @Test
    public void titleShouldNotBeNullAfterSet(){
        mEventsReport.setReportTitle("");
        String mTestString = mEventsReport.getReportTitle();
        assertNotNull(mTestString);
    }



}

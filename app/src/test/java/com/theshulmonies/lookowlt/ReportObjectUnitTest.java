package com.theshulmonies.lookowlt;



import com.theshulmonies.lookowlt.Reports.EventsReport;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by davidseverns on 11/8/17.
 */

public class ReportObjectUnitTest {
    EventsReport mEventsReport;

    public void setup(){
        mEventsReport = new EventsReport();
    }

    @Test
    public void shouldDecrementCredibilityScore(){
        mEventsReport.userDownVote();
        assertEquals(0, mEventsReport.getCredibitlyScore());
    }
}

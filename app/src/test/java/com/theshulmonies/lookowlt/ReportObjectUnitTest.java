package com.theshulmonies.lookowlt;



import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by davidseverns on 11/8/17.
 */

public class ReportObjectUnitTest {
    Reportable eventsReport;

    public void setup(){
        eventsReport = new EventsReport();
    }

    @Test
    public void shouldDecrementCredibilityScore(){
        eventsReport.userDownVote();
        assertEquals(0,eventsReport.getCredibitlyScore());
    }
}

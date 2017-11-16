package com.theshulmonies.lookowlt.Reports;

/**
 * Created by davidseverns on 11/12/17.
 */

public class FreeStuffReport extends EventsReport {

    private final String DEFAULT_HEADER = "FREE STUFF ALERT!!!";



    private String eventHeader;

    public FreeStuffReport(){
        super();
    }
    public void setEventHeader(String eventTitle){
         eventHeader = DEFAULT_HEADER + " " + eventTitle;


    }
    public String getEventHeader() {
        return eventHeader;
    }
}

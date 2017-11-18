package com.theshulmonies.lookowlt.Reports;

import android.graphics.Color;

import com.theshulmonies.lookowlt.R;
import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

/**
 * The report factory class will be responsible for creating the correct type of report
 * based on the selection made by the user, the user will select a type of report and the factory will instantiate the
 * report based on the selection.
 * Created by davidseverns on 11/16/17.
 */

public class ReportFactory {
    private final String EMERGENCY_COLOR = "Red" ;
    private final String MAINTENANCE_COLOR = "Yellow" ;
    private final String FREE_STUFF_COLOR = "Blue";

    FirebaseUtility mFirebaseUtility;

    /*
    @param reportTypeColor takes in a Color object assigned when the user selects the report type
    @return returns and object of EventsReport with the correct instantiation based on the
    type that was recieved.
     */
    public EventsReport getReport(String reportTypeColor){

        if(reportTypeColor.equals(EMERGENCY_COLOR)){
            return new EmergencyReport();
        }
        else if(reportTypeColor.equals(MAINTENANCE_COLOR)){
            return new MaintenanceReport();
        }
        else if(reportTypeColor.equals(FREE_STUFF_COLOR)){
            return new FreeStuffReport();
        }
        //default
        else {return new EventsReport();}


    }
}

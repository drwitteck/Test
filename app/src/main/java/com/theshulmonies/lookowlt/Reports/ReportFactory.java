package com.theshulmonies.lookowlt.Reports;

import android.graphics.Color;

import com.theshulmonies.lookowlt.Utilities.FirebaseUtility;

/**
 * The report factory class will be responsible for creating the correct type of report
 * based on the selection made by the user, the user will select a type of report and the factory will instantiate the
 * report based on the selection.
 * Created by davidseverns on 11/16/17.
 */

public class ReportFactory {
    private final int EMERGENCY_COLOR = Color.RED ;
    private final int MAINTENANCE_COLOR = Color.YELLOW;
    private final int FREE_STUFF_COLOR = Color.BLUE;

    FirebaseUtility mFirebaseUtility;

    public EventsReport getReport(Color color){
        return null;
    }
}

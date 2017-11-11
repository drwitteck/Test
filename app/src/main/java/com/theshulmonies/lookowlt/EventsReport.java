package com.theshulmonies.lookowlt;

import android.graphics.Color;
import android.location.Location;

import java.util.Date;

/**
 * Created by davidseverns on 11/8/17.
 */

public class EventsReport implements Reportable {

    private String reportTitle;
    private Date dateAdded;
    private boolean specialReport;
    //location object holds lattitude and logintube of gps location
    private Location reportLatLong;
    private Color eventsTypeMarker;
    private long reportCredibilityScore;

    public EventsReport(){

    }

    @Override
    public void userUpVote() {

    }

    @Override
    public void userDownVote() {

    }

    public int getCredibitlyScore(){
        return 0;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isSpecialReport() {
        return specialReport;
    }

    public void setSpecialReport(boolean specialReport) {
        this.specialReport = specialReport;
    }

    public Location getReportLatLong() {
        return reportLatLong;
    }

    public void setReportLatLong(Location reportLatLong) {
        this.reportLatLong = reportLatLong;
    }

    public Color getEventsTypeMarker() {
        return eventsTypeMarker;
    }

    public void setEventsTypeMarker(Color eventsTypeMarker) {
        this.eventsTypeMarker = eventsTypeMarker;
    }
}

package com.theshulmonies.lookowlt.Reports;

import android.graphics.Color;
import android.location.Location;

import java.util.Date;

/**
 * Created by davidseverns on 11/8/17.
 */

public class EventsReport implements Reportable {

    private String reportTitle;
    private String reportDetails;
    private Date dateAdded;
    private boolean specialReport;
    //location object holds lattitude and logintube of gps location
    private Location reportLatLong;
    private Color eventsTypeMarker;
    private long reportCredibilityScore;

    public EventsReport(){

        //when report is created score set to 0
        reportCredibilityScore = 0;

    }

    /**
     * this method takes no parameters but serves the purpose of
     * incrementing the reports credibility score
     */
    @Override
    public void userUpVote() {


    }

    /**
     * this method takes no parameters but serves the purpose of
     * decrementing the reports credibility score
     */
    @Override
    public void userDownVote() {

    }

    public long getReportCredibilityScore(){
        return reportCredibilityScore;
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

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public void sendSpecialReport(){

    }

    /**
     * gets the latitude longitude  from the devices gps location and returns it in a
     * location object
     * @return Location reportLocation
     */
    public Location getLocationFromDevice(){
        return null;
    }
}

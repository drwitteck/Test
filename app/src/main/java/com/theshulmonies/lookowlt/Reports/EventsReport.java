package com.theshulmonies.lookowlt.Reports;

import android.graphics.Color;


import java.util.Date;

/**
 * Created by davidseverns on 11/8/17.
 */

public class EventsReport implements Reportable {

    private String reportTitle;
    private String reportDetails;
    private String reportImage;
    private Date dateAdded;
    private boolean specialReport;

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
        reportCredibilityScore += 1;
    }

    /**
     * this method takes no parameters but serves the purpose of
     * decrementing the reports credibility score
     */
    @Override
    public void userDownVote() {
        reportCredibilityScore -= 1;
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
        specialReport = true;
    }

    public String getImage() {
        return reportImage;
    }

    public void setImage(String image) {
        this.reportImage = image;
    }
}

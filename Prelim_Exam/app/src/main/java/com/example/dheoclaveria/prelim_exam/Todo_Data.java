package com.example.dheoclaveria.prelim_exam;

/**
 * Created by Dheo Claveria on 7/22/2017.
 */

public class Todo_Data {

    String id, title,details,frmdate,frmtime,todate,totime;
    int prioritylevel;
    public Todo_Data(String id, String title, String details, String frmdate, String frmtime, String todate, String totime,int prioritylevel){

        this.id = id;
        this.title = title;
        this.details = details;
        this.frmdate = frmdate;
        this.frmtime = frmtime;
        this.todate = todate;
        this.totime = totime;
        this.prioritylevel = prioritylevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFrmdate() {
        return frmdate;
    }

    public void setFrmdate(String frmdate) {
        this.frmdate = frmdate;
    }

    public String getFrmtime() {
        return frmtime;
    }

    public void setFrmtime(String frmtime) {
        this.frmtime = frmtime;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public int getPrioritylevel() {
        return prioritylevel;
    }

    public void setPrioritylevel(int prioritylevel) {
        this.prioritylevel = prioritylevel;
    }
}




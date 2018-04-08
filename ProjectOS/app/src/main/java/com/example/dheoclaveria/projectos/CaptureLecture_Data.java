package com.example.dheoclaveria.projectos;

/**
 * Created by Dheo Claveria on 10/1/2017.
 */

public class CaptureLecture_Data {

    String id, title, subjectname, decription, keyword, date, time,image;

    public CaptureLecture_Data(String id, String title, String subjectname, String description, String keyword, String date, String time, String image) {

        this.id = id;
        this.title = title;
        this.subjectname = subjectname;
        this.decription= description;
        this.keyword = keyword;
        this.date = date;
        this.time = time;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getDecription() {
        return decription;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

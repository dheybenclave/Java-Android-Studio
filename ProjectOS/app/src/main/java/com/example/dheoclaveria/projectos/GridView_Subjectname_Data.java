package com.example.dheoclaveria.projectos;

/**
 * Created by Dheo Claveria on 10/2/2017.
 */

public class GridView_Subjectname_Data {

    // for gridgroup tile only;;
    String id, title,image, color;

    public GridView_Subjectname_Data(String id, String title, String image, String color)
    {
        this.id = id;
        this.title = title;
        this.image = image;
        this.color = color;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

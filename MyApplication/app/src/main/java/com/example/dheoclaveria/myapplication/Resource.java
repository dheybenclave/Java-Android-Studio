package com.example.dheoclaveria.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;


/**
 * Created by Dheo Claveria on 10/7/2017.
 */

public class Resource {

    public static Uri subimage ;
    public static  ColorDrawable subcolor;


    public  Uri forimagechoice(String imagename) {

        try {

            switch (imagename) {
                case "book":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.book);
                    break;
                case "bookmark":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.bookmark);
                    break;
                case "bookopen":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.bookopen);
                    break;
                case "books":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.books);
                    break;
                case "compass":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.compass);
                    break;
                case "diploma":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.diploma);
                    break;
                case "erase":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.erase);
                    break;
                case "formula":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.formula);
                    break;
                case "notepad":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.notepad);
                    break;
                case "pipette":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.pipette);
                    break;
                case "projection":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.projection);
                    break;
                case "quill":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.quill);
                    break;
                case "studenthat":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.studenthat);
                    break;
                case "transparent":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.transparent);
                    break;
                default:
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.book);

            }

        } catch (Exception ex) {}
        return subimage;
    }

    public  ColorDrawable forcolorchoice(String color) {
        switch (color) {
            case "dark":
                subcolor = new ColorDrawable(Color.parseColor("#242424"));
                break;
            case "red":
                subcolor = new ColorDrawable(Color.parseColor("#f6402b"));
                break;
            case "darkpink":
                subcolor = new ColorDrawable(Color.parseColor("#eb1461"));
                break;
            case "violet":
                subcolor = new ColorDrawable(Color.parseColor("#9c1ab2"));
                break;
            case "blue":
                subcolor = new ColorDrawable(Color.parseColor("#1193f5"));
                break;
            case "lightblue":
                subcolor = new ColorDrawable(Color.parseColor("#00bbd6"));
                break;
            case "bluegreen":
                subcolor = new ColorDrawable(Color.parseColor("#009788"));
                break;
            case "green":
                subcolor = new ColorDrawable(Color.parseColor("#01b876"));
                break;
            case "yellow":
                subcolor = new ColorDrawable(Color.parseColor("#ffec16"));
                break;
            case "orange":
                subcolor = new ColorDrawable(Color.parseColor("#ff9801"));
                break;
            case "darkorange":
                subcolor = new ColorDrawable(Color.parseColor("#ff5505"));
                break;
            case "darkbrown":
                subcolor = new ColorDrawable(Color.parseColor("#7a5447"));
                break;
            case "silver":
                subcolor = new ColorDrawable(Color.parseColor("#9d9d9d"));
                break;
            case "gray":
                subcolor = new ColorDrawable(Color.parseColor("#5f7c8c"));
                break;

            default:
                subcolor = new ColorDrawable(Color.parseColor("#5f7c8c"));
        }
        return subcolor;
    }

}

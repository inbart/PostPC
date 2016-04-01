package com.example.inbar.todolistmanager;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ofer on 17/3/2016.
 */
public class Item {
    private String title;
    private Date date;
    private int id;


    public Item(String title, Date date){
        super();
        this.title = title;
        this.date = date;
        id = 0;
    }

    public String getTitle(){
        return title;
    }

    public Date getDate(){
        return date;
    }

    public int getId() { return id; }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setId(int id) { this.id = id; }

    public String convertDateToString(Date date){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(date);
    }

    public String toString(){
        return "title = " + title + " , " + "due = " + date;
    }

}

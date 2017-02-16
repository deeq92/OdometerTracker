package com.example.diazapps.odometertracker;

import java.text.SimpleDateFormat;

public class MyEntry {

    private int startOdo;
    private int endOdo;
    private int milesDriven;
    private String date;
    private int entryID;
    private String note;

    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");


    public int getStartOdo() {
        return startOdo;
    }

    public void setStartOdo(int startOdo) {
        this.startOdo = startOdo;
    }

    public int getEndOdo() {
        return endOdo;
    }

    public void setEndOdo(int endOdo) {
        this.endOdo = endOdo;
    }

    public int getMilesDriven() {
        return milesDriven;
    }

    public void setMilesDriven(int milesDriven) {
        this.milesDriven = milesDriven;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

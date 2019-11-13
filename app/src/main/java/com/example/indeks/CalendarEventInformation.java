package com.example.indeks;

public class CalendarEventInformation {
    private String Description;
    private String Date;
    private String Time;

    public CalendarEventInformation() {}

    public CalendarEventInformation(String description, String date, String time) {
        this.Description = description;
        this.Date = date;
        this.Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

package com.example.noteai;

public class Notes {
    private String Title,Name,Date;
    public Notes(){

    }
    public Notes(String title, String name, String date) {
        Title = title;
        Name = name;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

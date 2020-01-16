package com.example.noteai;

public class Notes {
    private String Title,Name,Date,Main,Summary;
    public Notes(){

    }
    public Notes(String title, String name, String date,String main,String summary) {
        Title = title;
        Name = name;
        Date = date;
        Main=main;
        Summary=summary;
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
    public String getMain(){return Main;}
    public void setMain(String main){Main=main;}
    public String getSummary(){return Summary;}
    public void setSummary(String summary){Summary=summary;}


}

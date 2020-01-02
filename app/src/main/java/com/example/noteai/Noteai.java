package com.example.noteai;

import android.app.Application;

import com.firebase.client.Firebase;

public class Noteai extends Application {

    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}

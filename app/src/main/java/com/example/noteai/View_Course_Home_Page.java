package com.example.noteai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class View_Course_Home_Page extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuthenticate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__course__home__page);
        mAuthenticate=FirebaseAuth.getInstance();
        String auser_id=mAuthenticate.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Class Notes");

    }
}

package com.example.noteai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePagePersonalNotes extends AppCompatActivity {
private Button mView,mNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_personal_notes);
        mNew=findViewById(R.id.button3);
        mView=findViewById(R.id.button8);

        mNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePagePersonalNotes.this,Personal_Notes.class);
                startActivity(intent);
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePagePersonalNotes.this,CourseHomePage.class);
                startActivity(intent);
            }
        });
    }
}

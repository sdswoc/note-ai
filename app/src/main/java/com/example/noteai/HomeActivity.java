package com.example.noteai;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button mPersonal,mAdd,mView,mMy,mPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPersonal=findViewById(R.id.button6);
        mAdd=findViewById(R.id.button4);
        mView=findViewById(R.id.button5);
        mPdf=findViewById(R.id.button7);
        mPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,PdfConverter.class);
                startActivity(intent);
            }
        });
        mPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Personal_Notes.class);
                startActivity(intent);
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,AddCoursesActivity.class);
                startActivity(intent);
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CourseHomePage.class);
                startActivity(intent);
            }
        });
    }

}

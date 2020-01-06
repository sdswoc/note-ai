package com.example.noteai;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class About_Me_Activity extends AppCompatActivity {
private DatabaseReference mDatabase;
private EditText mName,mBranch,mYear,mEnrolment,mExtra;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__me_);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        mName=findViewById(R.id.name);
        mBranch=findViewById(R.id.branch);
        mYear=findViewById(R.id.year);
        mEnrolment=findViewById(R.id.enrolment_no);
        mExtra=findViewById(R.id.extra);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.getValue(String.class);
                mName.setText(name);
                String branch=dataSnapshot.getValue(String.class);
                mBranch.setText(branch);
                String year=dataSnapshot.getValue(String.class);
                mYear.setText(year);
                String enrolment=dataSnapshot.getValue(String.class);
                mEnrolment.setText(enrolment);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

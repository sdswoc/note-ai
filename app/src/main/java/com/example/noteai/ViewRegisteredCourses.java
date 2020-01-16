package com.example.noteai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewRegisteredCourses extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    private DatabaseReference mDatabase;
    private Spinner mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_courses);
        mList=findViewById(R.id.spinner1);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Courses");
        Query query=mDatabase.orderByChild("Code");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> courses=new ArrayList<String>();
                for(DataSnapshot courseSnapshot:dataSnapshot.getChildren()){
                    String courseName=courseSnapshot.child("Code").getValue(String.class);
                    courses.add(courseName);
                }
                ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(ViewRegisteredCourses.this,android.R.layout.simple_spinner_item,courses);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mList.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    }



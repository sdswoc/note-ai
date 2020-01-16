package com.example.noteai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddCoursesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
private Button mAdd;
private TextView mCheck;
private EditText mCourse;
private FirebaseAuth mAuth;
private DatabaseReference mDatabase;
private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        mAdd=findViewById(R.id.button);
        mCheck=findViewById(R.id.textView20);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Courses");
        mProgress = new ProgressDialog(this);
        mCourse=findViewById(R.id.course_code);
        mAuth=FirebaseAuth.getInstance();
        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddCoursesActivity.this,ViewRegisteredCourses.class);
                startActivity(intent);
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=mCourse.getText().toString().trim();
                mProgress.setMessage("Adding the course");
                mProgress.show();
                if(!TextUtils.isEmpty(code)){
                    String user_id=mAuth.getCurrentUser().getUid();
                    DatabaseReference new_course=mDatabase.push();
                    new_course.child("Code").setValue(code);
                    new_course.child("UID").setValue(user_id);
                    mProgress.dismiss();
                    Toast.makeText(AddCoursesActivity.this,"Course added successfully",Toast.LENGTH_LONG).show();
                }
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

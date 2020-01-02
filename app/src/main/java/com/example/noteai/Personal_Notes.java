package com.example.noteai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Personal_Notes extends AppCompatActivity {
    private EditText mTitle,mDate,mName,mMain,mKey,mSummary;
    private DatabaseReference mDatabase_personal;
    private Button mAdd;
    private FirebaseAuth mAuthenticate;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__notes);
        mTitle=findViewById(R.id.editText12);
        mDate=findViewById(R.id.editText2);
        mName=findViewById(R.id.editText3);
        mMain=findViewById(R.id.editText6);
        mAuthenticate=FirebaseAuth.getInstance();
        mKey=findViewById(R.id.editText5);
        mSummary=findViewById(R.id.editText4);
        mDialog=new ProgressDialog(this);
        mAdd=findViewById(R.id.button2);
        mDatabase_personal= FirebaseDatabase.getInstance().getReference().child("Personal Notes");
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mTitle.getText().toString().trim();
                String date=mDate.getText().toString().trim();
                String name=mName.getText().toString().trim();
                String main=mName.getText().toString().trim();
                String keyword=mKey.getText().toString().trim();
                String summary=mSummary.getText().toString().trim();
                if(!TextUtils.isEmpty(title)||!TextUtils.isEmpty(date)||!TextUtils.isEmpty(name)||!TextUtils.isEmpty(main)||!TextUtils.isEmpty(keyword)||!TextUtils.isEmpty(summary)){
                    mDialog.setMessage("Adding Notes");
                    mDialog.show();
                    String auser_id=mAuthenticate.getCurrentUser().getUid();
                    DatabaseReference new_note=mDatabase_personal.push();
                    new_note.child("Title").setValue(title);
                    new_note.child("Date").setValue(date);
                    new_note.child("Name").setValue(name);
                    new_note.child("Main Notes").setValue(main);
                    new_note.child("Keywords").setValue(keyword);
                    new_note.child("Summary").setValue(summary);
                    new_note.child("UID").setValue(auser_id);
                    mDialog.dismiss();
                    Intent intent=new Intent(Personal_Notes.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(Personal_Notes.this,"Notes added successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Personal_Notes.this,"Take some notes!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

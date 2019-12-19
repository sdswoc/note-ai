package com.example.noteai.ui.login;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.noteai.HomeActivity;
import com.example.noteai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    EditText mName, mEnrolment, mBranch, mYear, mPass, mConfirmPass, mRecovery;
    Button mSignUp;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView mLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mLogin=findViewById(R.id.textView26);
        mName = findViewById(R.id.name);
        mEnrolment = findViewById(R.id.enrolmentno);
        mBranch = findViewById(R.id.branch);
        mYear = findViewById(R.id.year);
        mPass = findViewById(R.id.password);
        mConfirmPass = findViewById(R.id.confirm_password);
        mRecovery = findViewById(R.id.recovery_email);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.loading);
        mSignUp=findViewById(R.id.sign_up);
        if(fAuth.getCurrentUser()!=null)
        {startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();}
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name=mName.getText().toString().trim();
                String enrolment=mEnrolment.getText().toString().trim();
                String branch=mBranch.getText().toString().trim();
                String year=mYear.getText().toString().trim();
                String pass=mPass.getText().toString().trim();
                String confirmpass=mConfirmPass.getText().toString().trim();
                String recovery=mRecovery.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    mName.setError("Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(enrolment))
                {
                    mEnrolment.setError("Enrolment No. is Required");
                    return;
                }
                if(TextUtils.isEmpty(branch))
                {
                    mBranch.setError("Branch is Required");
                    return;
                }
                if(TextUtils.isEmpty(year))
                {
                    mYear.setError("Year is Required");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    mPass.setError("Password is Required");
                    return;
                }
                if(TextUtils.isEmpty(confirmpass))
                {
                    mConfirmPass.setError("Password doesn't match");
                    return;
                }
                if(TextUtils.isEmpty(recovery))
                {
                    mRecovery.setError("Recovery Email is Required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(enrolment,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));}
                        else{
                            Toast.makeText(SignUpActivity.this, "Error has occured"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

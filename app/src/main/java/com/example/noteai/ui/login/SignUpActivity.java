package com.example.noteai.ui.login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteai.HomeActivity;
import com.example.noteai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText mName, mEnrolment, mBranch, mYear, mPass, mConfirmPass, mEmail;
    private Button mSignUp;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private TextView mLogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mLogin = findViewById(R.id.textView26);
        mName = findViewById(R.id.name);
        mEnrolment = findViewById(R.id.enrolmentno);
        mBranch = findViewById(R.id.branch);
        mYear = findViewById(R.id.year);
        mAuth=FirebaseAuth.getInstance();
        mPass = findViewById(R.id.password);
        mConfirmPass = findViewById(R.id.confirm_password);
        mEmail = findViewById(R.id.email);
        mProgress = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mSignUp = findViewById(R.id.sign_up);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString().trim();
                final String enrolment = mEnrolment.getText().toString().trim();
                final String branch = mBranch.getText().toString().trim();
                final String year = mYear.getText().toString().trim();
                final String password = mPass.getText().toString().trim();
                final String confirmpass = mConfirmPass.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(SignUpActivity.this,"Please enter your Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(enrolment))
                {
                    Toast.makeText(SignUpActivity.this,"Please enter your Enrolment Number",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignUpActivity.this,"Please enter a Password",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!password.equals(confirmpass))
                {
                    Toast.makeText(SignUpActivity.this,"Passwords don't match",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignUpActivity.this,"Please enter an Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(enrolment)&&!TextUtils.isEmpty(branch)&&!TextUtils.isEmpty(year)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(confirmpass)&&!TextUtils.isEmpty(email))
                mProgress.setMessage("Signing Up");
                mProgress.show();
                {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              String user_id=mAuth.getCurrentUser().getUid();
                              DatabaseReference new_user=mDatabase.child(user_id);
                              new_user.child("Name").setValue(name);
                              new_user.child("Enrolment").setValue(enrolment);
                              new_user.child("Branch").setValue(branch);
                              new_user.child("Year").setValue(year);
                              new_user.child("Password").setValue(password);
                              new_user.child("Confirm Password").setValue(confirmpass);
                              new_user.child("Email").setValue(email);
                              mProgress.dismiss();
                              Intent intent=new Intent(SignUpActivity.this, HomeActivity.class);
                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                              startActivity(intent);
                          }
                        }
                    });

                }
            }
        }
        );
    }
}


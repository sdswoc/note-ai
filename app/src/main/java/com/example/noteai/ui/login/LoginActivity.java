package com.example.noteai.ui.login;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.noteai.HomeActivity;
import com.example.noteai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
   private EditText mEmail,mPass;
   private TextView mRegister;
   private Button mLogin;
   private FirebaseAuth mAuth;
   private DatabaseReference mData;
   private ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.username_login);
        mPass=findViewById(R.id.password_login);
        mRegister=findViewById(R.id.textView25);
        mProgress=new ProgressDialog(this);
        mLogin=findViewById(R.id.loginbtn);
        mData= FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth=FirebaseAuth.getInstance();
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String pass=mPass.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this,"Please enter your Email-ID",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(LoginActivity.this,"Please enter your Password",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){
                    mProgress.setMessage("Logging in");
                    mProgress.show();
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mProgress.dismiss();
                                final String user_id=mAuth.getCurrentUser().getUid();
                                mData.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild(user_id)){
                                            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(LoginActivity.this,"Error Logging in.Setup your account first.",Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else {
                                mProgress.dismiss();
                                Toast.makeText(LoginActivity.this,"Error Logging in.Please Check your Internet Connection.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}



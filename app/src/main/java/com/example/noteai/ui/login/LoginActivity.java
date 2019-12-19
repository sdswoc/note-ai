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

public class LoginActivity extends AppCompatActivity {

    EditText mEnrolment,mPass;
    TextView mRegister;
    Button mbutton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegister=findViewById(R.id.textView25);
        mEnrolment=findViewById(R.id.username);
        mPass= findViewById(R.id.password);
        mbutton=findViewById(R.id.login);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.load);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enrolment=mEnrolment.getText().toString().trim();
                String pass=mPass.getText().toString().trim();
                if(TextUtils.isEmpty(enrolment))
                {
                    mEnrolment.setError("Enrolment No. is Required");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    mPass.setError("Password is Required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(enrolment,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error has occured" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }});
            }
        });
    }
}



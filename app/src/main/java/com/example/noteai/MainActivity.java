package com.example.noteai;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.noteai.ui.login.LoginActivity;
import com.example.noteai.ui.login.SignUpActivity;
public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
        button2=(Button)findViewById(R.id.button_register);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }
    public void openLoginActivity() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openSignUpActivity() {
        Intent intent=new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

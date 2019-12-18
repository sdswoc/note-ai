package com.example.noteai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.noteai.ui.login.LoginActivity;


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


    }

    public void openLoginActivity() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

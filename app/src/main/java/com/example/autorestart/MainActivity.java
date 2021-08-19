package com.example.autorestart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
//        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, MainActivity.class));



        if (getIntent().getBooleanExtra("crash", false)){
            Toast.makeText(this,"App restarted after crash",Toast.LENGTH_SHORT).show();
        }
    }
    public void crashMe(View v){
        throw new NullPointerException();
    }
}
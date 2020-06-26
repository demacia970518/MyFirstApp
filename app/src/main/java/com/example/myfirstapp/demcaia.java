package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class demcaia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void SendMessage(View view){
        Intent intent = new Intent(this,relative.class);
        startActivity(intent);
    }

    public void SendMessage1(View view){
        Intent intent = new Intent(this,webView.class);
        startActivity(intent);
    }




}